package academy.wakanda.delivery.cliente.application.service;

import academy.wakanda.delivery.cliente.application.api.*;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    ClienteResponse criaCliente(ClienteRequest clienteRequest);
    List<ClienteListResponse> buscaTodosClientes();
    ClienteDetalhadoResponse buscaClientePorId(UUID idCliente);
    void atualizaClientePorId(UUID idCliente, ClienteAlteracaoRequest clienteAlteracaoRequest);
}
