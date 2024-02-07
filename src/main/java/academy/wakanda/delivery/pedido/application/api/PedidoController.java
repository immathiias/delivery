package academy.wakanda.delivery.pedido.application.api;

import academy.wakanda.delivery.pedido.application.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@Log4j2
@RequiredArgsConstructor
public class PedidoController implements PedidoAPI {
    private final PedidoService pedidoService;

    @Override
    public PedidoResponse postClienteRealizaPedidoCriandoEndereco(String token, UUID idCliente, PedidoRequestCriandoEndereco pedidoRequest) {
        log.info("[inicia] PedidoController - postClienteRealizaPedido");
        PedidoResponse pedido = pedidoService.clienteRealizaPedidoCriandoEndereco(token, idCliente, pedidoRequest);
        log.info("[finaliza] PedidoController - postClienteRealizaPedido");
        return pedido;
    }
}
