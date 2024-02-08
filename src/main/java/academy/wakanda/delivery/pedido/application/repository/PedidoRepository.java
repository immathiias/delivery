package academy.wakanda.delivery.pedido.application.repository;

import academy.wakanda.delivery.pedido.domain.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository {
    Pedido salvaPedido(Pedido pedido);
    List<Pedido> buscaTodosPedidosDoCliente(UUID idCliente);
    Pedido buscaPedidoDoClientePorId(UUID idCliente, UUID idPedido);
}
