package academy.wakanda.delivery.pedido.application.api;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class PedidoResponse {
    private UUID idPedido;
    private UUID idCliente;
}
