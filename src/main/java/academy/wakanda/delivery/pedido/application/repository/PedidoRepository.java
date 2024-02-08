package academy.wakanda.delivery.pedido.application.repository;

import academy.wakanda.delivery.pedido.domain.Pedido;

public interface PedidoRepository {
    Pedido salvaPedido(Pedido pedido);
}
