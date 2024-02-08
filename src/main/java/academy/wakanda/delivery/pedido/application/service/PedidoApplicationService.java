package academy.wakanda.delivery.pedido.application.service;

import academy.wakanda.delivery.cliente.application.api.EnderecoRequest;
import academy.wakanda.delivery.cliente.application.repository.ClienteRepository;
import academy.wakanda.delivery.cliente.application.service.ClienteService;
import academy.wakanda.delivery.cliente.domain.Cliente;
import academy.wakanda.delivery.cliente.domain.Endereco;
import academy.wakanda.delivery.config.security.service.TokenService;
import academy.wakanda.delivery.handler.APIException;
import academy.wakanda.delivery.pedido.application.api.*;
import academy.wakanda.delivery.pedido.application.repository.PedidoRepository;
import academy.wakanda.delivery.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PedidoApplicationService implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final TokenService tokenService;

    @Override
    public PedidoResponse clienteRealizaPedidoCriandoEndereco(String token, UUID idCliente, PedidoRequestCriandoEndereco pedidoRequest) {
        log.info("[inicia] PedidoApplicationService - clienteRealizaPedido");
        String clienteEmail = tokenService.getEmailByBearerToken(token)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Cliente não encontrado."));

        Cliente cliente = clienteRepository.buscaClientePorEmail(clienteEmail);
        cliente.validaCliente(idCliente);
        Endereco endereco = new Endereco(pedidoRequest.getEnderecoEntrega());

        Pedido pedido = pedidoRepository.salvaPedido(new Pedido(idCliente, pedidoRequest, endereco));
        clienteService.adicionaEnderecoEPedidoCliente(cliente, pedido, pedidoRequest.getEnderecoEntrega());

        log.info("[finaliza] PedidoApplicationService - clienteRealizaPedido");
        return PedidoResponse.builder()
                .idPedido(pedido.getIdPedido())
                .idCliente(pedido.getIdCliente())
                .build();
    }

    @Override
    public List<PedidoListCliente> buscaTodosPedidosDoCliente(String token, UUID idCliente) {
        log.info("[inicia] PedidoApplicationService - buscaTodosPedidosDoCliente");
        String clienteEmail = tokenService.getEmailByBearerToken(token)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Cliente não encontrado."));
        Cliente cliente = clienteRepository.buscaClientePorEmail(clienteEmail);
        cliente.validaCliente(idCliente);
        List<Pedido> pedidosDoCliente = pedidoRepository.buscaTodosPedidosDoCliente(idCliente);
        log.info("[finaliza] PedidoApplicationService - buscaTodosPedidosDoCliente");
        return PedidoListCliente.converte(pedidosDoCliente);
    }

    @Override
    public PedidoDetalhadoCliente buscaPedidoDoClientePorId(String token, UUID idCliente, UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - buscaPedidoDoClientePorId");
        String clienteEmail = tokenService.getEmailByBearerToken(token)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Cliente não encontrado."));
        Cliente cliente = clienteRepository.buscaClientePorEmail(clienteEmail);
        cliente.validaCliente(idCliente);

        Pedido pedido = pedidoRepository.buscaPedidoDoClientePorId(idCliente, idPedido);

        log.info("[finaliza] PedidoApplicationService - buscaPedidoDoClientePorId");
        return new PedidoDetalhadoCliente(pedido);
    }

    @Override
    public void alteraPedidoDoClientePorId(String token, UUID idCliente, UUID idPedido, PedidoAlteracaoRequest pedidoAlteracaoRequest) {
        log.info("[inicia] PedidoApplicationService - alteraPedidoDoClientePorId");
        String clienteEmail = tokenService.getEmailByBearerToken(token)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Cliente não encontrado."));
        Cliente cliente = clienteRepository.buscaClientePorEmail(clienteEmail);
        cliente.validaCliente(idCliente);

        Pedido pedido = pedidoRepository.buscaPedidoDoClientePorId(idCliente, idPedido);
        pedido.altera(pedidoAlteracaoRequest);
        pedidoRepository.salvaPedido(pedido);
        log.info("[finaliza] PedidoApplicationService - alteraPedidoDoClientePorId");
    }
}
