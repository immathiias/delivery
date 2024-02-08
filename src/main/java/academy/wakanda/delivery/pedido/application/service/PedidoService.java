package academy.wakanda.delivery.pedido.application.service;

import academy.wakanda.delivery.pedido.application.api.PedidoListCliente;
import academy.wakanda.delivery.pedido.application.api.PedidoRequestCriandoEndereco;
import academy.wakanda.delivery.pedido.application.api.PedidoResponse;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoResponse clienteRealizaPedidoCriandoEndereco(String token, UUID idCliente, PedidoRequestCriandoEndereco pedidoRequest);
    List<PedidoListCliente> buscaTodosPedidosDoCliente(String token, UUID idCliente);
}
