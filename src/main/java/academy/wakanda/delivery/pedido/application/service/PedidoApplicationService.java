package academy.wakanda.delivery.pedido.application.service;

import academy.wakanda.delivery.cliente.application.service.ClienteService;
import academy.wakanda.delivery.endereco.application.service.EnderecoService;
import academy.wakanda.delivery.endereco.domain.Endereco;
import academy.wakanda.delivery.pedido.application.api.*;
import academy.wakanda.delivery.pedido.application.repository.PedidoRepository;
import academy.wakanda.delivery.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PedidoApplicationService implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final EnderecoService enderecoService;

    @Override
    public PedidoResponse clienteRealizaPedidoCriandoEndereco(String token, UUID idCliente, PedidoRequestCriandoEndereco pedidoRequest) {
        log.info("[inicia] PedidoApplicationService - clienteRealizaPedido");
        clienteService.checaCliente(token, idCliente);
        Endereco endereco = new Endereco(idCliente, pedidoRequest.getEnderecoEntrega());

        Pedido pedido = pedidoRepository.salvaPedido(new Pedido(idCliente, pedidoRequest, endereco.getIdEndereco()));
        enderecoService.adicionaEnderecoCliente(idCliente, pedidoRequest.getEnderecoEntrega());
        log.info("[finaliza] PedidoApplicationService - clienteRealizaPedido");
        return PedidoResponse.builder()
                .idPedido(pedido.getIdPedido())
                .idCliente(pedido.getIdCliente())
                .build();
    }

    @Override
    public PedidoResponse clienteRealizaPedido(String token, UUID idCliente, PedidoRequest pedidoRequest) {
        log.info("[inicia] PedidoApplicationService - clienteRealizaPedido");
        clienteService.checaCliente(token, idCliente);

        Pedido pedido = pedidoRepository.salvaPedido(new Pedido(idCliente, pedidoRequest));
        log.info("[finaliza] PedidoApplicationService - clienteRealizaPedido");
        return PedidoResponse.builder()
                .idPedido(pedido.getIdPedido())
                .idCliente(pedido.getIdCliente())
                .build();
    }

    @Override
    public List<PedidoListCliente> buscaTodosPedidosDoCliente(String token, UUID idCliente) {
        log.info("[inicia] PedidoApplicationService - buscaTodosPedidosDoCliente");
        clienteService.checaCliente(token, idCliente);

        List<Pedido> pedidosDoCliente = pedidoRepository.buscaTodosPedidosDoCliente(idCliente);
        log.info("[finaliza] PedidoApplicationService - buscaTodosPedidosDoCliente");
        return PedidoListCliente.converte(pedidosDoCliente);
    }

    @Override
    public PedidoDetalhadoCliente buscaPedidoDoClientePorId(String token, UUID idCliente, UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - buscaPedidoDoClientePorId");
        clienteService.checaCliente(token, idCliente);

        Pedido pedido = pedidoRepository.buscaPedidoDoClientePorId(idCliente, idPedido);

        log.info("[finaliza] PedidoApplicationService - buscaPedidoDoClientePorId");
        return new PedidoDetalhadoCliente(pedido);
    }

    @Override
    public void alteraPedidoDoClientePorId(String token, UUID idCliente, UUID idPedido, PedidoAlteracaoRequest pedidoAlteracaoRequest) {
        log.info("[inicia] PedidoApplicationService - alteraPedidoDoClientePorId");
        clienteService.checaCliente(token, idCliente);

        Pedido pedido = pedidoRepository.buscaPedidoDoClientePorId(idCliente, idPedido);
        pedido.altera(pedidoAlteracaoRequest);
        pedidoRepository.salvaPedido(pedido);
        log.info("[finaliza] PedidoApplicationService - alteraPedidoDoClientePorId");
    }

    @Override
    public void entregaPedidoDoCliente(String token, UUID idCliente, UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - entregaPedidoDoCliente");
        clienteService.checaCliente(token, idCliente);

        Pedido pedido = pedidoRepository.buscaPedidoDoClientePorId(idCliente, idPedido);
        pedido.realizaEntrega();
        pedidoRepository.salvaPedido(pedido);
        log.info("[finaliza] PedidoApplicationService - entregaPedidoDoCliente");
    }

    @Override
    public void retiraEntregaPedidoDoCliente(String token, UUID idCliente, UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - retiraEntregaPedidoDoCliente");
        clienteService.checaCliente(token, idCliente);

        Pedido pedido = pedidoRepository.buscaPedidoDoClientePorId(idCliente, idPedido);
        pedido.retiraEntrega();
        pedidoRepository.salvaPedido(pedido);
        log.info("[finaliza] PedidoApplicationService - retiraEntregaPedidoDoCliente");
    }

    @Override
    public void deletaPedidoDoClientePorId(String token, UUID idCliente, UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - deletaPedidoDoClientePorId");
        clienteService.checaCliente(token, idCliente);

        Pedido pedido = pedidoRepository.buscaPedidoDoClientePorId(idCliente, idPedido);
        pedidoRepository.deletaPedido(pedido);
        log.info("[finaliza] PedidoApplicationService - deletaPedidoDoClientePorId");
    }
}
