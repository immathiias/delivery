package academy.wakanda.delivery.cliente.application.service;

import academy.wakanda.delivery.cliente.application.api.*;
import academy.wakanda.delivery.cliente.domain.Cliente;
import academy.wakanda.delivery.pedido.domain.Pedido;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    ClienteResponse criaCliente(ClienteRequest clienteRequest);
    List<ClienteListResponse> buscaTodosClientes();
    ClienteDetalhadoResponse buscaClientePorId(UUID idCliente);
    void atualizaClientePorId(String token, UUID idCliente, ClienteAlteracaoRequest clienteAlteracaoRequest);
    void adicionaEnderecoCliente(UUID idCliente, EnderecoRequest enderecoRequest);
    void adicionaEnderecoEPedidoCliente(Cliente cliente, Pedido pedido, EnderecoRequest enderecoRequest);
    void deletaClientePorId(String token, UUID idCliente);
}
