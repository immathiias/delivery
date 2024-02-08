package academy.wakanda.delivery.pedido.application.service;

import academy.wakanda.delivery.cliente.application.api.EnderecoRequest;
import academy.wakanda.delivery.cliente.application.repository.ClienteRepository;
import academy.wakanda.delivery.cliente.application.service.ClienteService;
import academy.wakanda.delivery.cliente.domain.Cliente;
import academy.wakanda.delivery.cliente.domain.Endereco;
import academy.wakanda.delivery.config.security.service.TokenService;
import academy.wakanda.delivery.pedido.application.api.PedidoRequest;
import academy.wakanda.delivery.pedido.application.api.PedidoRequestCriandoEndereco;
import academy.wakanda.delivery.pedido.application.api.PedidoResponse;
import academy.wakanda.delivery.pedido.application.repository.PedidoRepository;
import academy.wakanda.delivery.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Cliente cliente = clienteRepository.buscaClientePorEmail(clienteEmail);
        Endereco endereco = new Endereco(pedidoRequest.getEnderecoEntrega());

        Pedido pedido = pedidoRepository.salvaPedido(new Pedido(idCliente, pedidoRequest, endereco));
        clienteService.adicionaEnderecoEPedidoCliente(cliente, pedido, pedidoRequest.getEnderecoEntrega());

        log.info("[finaliza] PedidoApplicationService - clienteRealizaPedido");
        return PedidoResponse.builder()
                .idPedido(pedido.getIdPedido())
                .idCliente(pedido.getIdCliente())
                .build();
    }
}
