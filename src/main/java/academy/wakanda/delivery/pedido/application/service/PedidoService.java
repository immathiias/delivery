package academy.wakanda.delivery.pedido.application.service;

import academy.wakanda.delivery.pedido.application.api.*;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoResponse clienteRealizaPedidoCriandoEndereco(String token, UUID idCliente, PedidoRequestCriandoEndereco pedidoRequest);
    PedidoResponse clienteRealizaPedido(String token, UUID idCliente, PedidoRequest pedidoRequest);
    List<PedidoListCliente> buscaTodosPedidosDoCliente(String token, UUID idCliente);
    PedidoDetalhadoCliente buscaPedidoDoClientePorId(String token, UUID idCliente, UUID idPedido);
    void alteraPedidoDoClientePorId(String token, UUID idCliente, UUID idPedido, PedidoAlteracaoRequest pedidoAlteracaoRequest);
    void entregaPedidoDoCliente(String token, UUID idCliente, UUID idPedido);
    void retiraEntregaPedidoDoCliente(String token, UUID idCliente, UUID idPedido);
    void deletaPedidoDoClientePorId(String token, UUID idCliente, UUID idPedido);
}
