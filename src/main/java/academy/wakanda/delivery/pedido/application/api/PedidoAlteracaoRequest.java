package academy.wakanda.delivery.pedido.application.api;

import lombok.Value;

@Value
public class PedidoAlteracaoRequest {
    private String produto;
    private String detalhesPedido;
}
