package academy.wakanda.delivery.cliente.application.service;

import academy.wakanda.delivery.cliente.application.api.ClienteListResponse;
import academy.wakanda.delivery.cliente.application.api.ClienteRequest;
import academy.wakanda.delivery.cliente.application.api.ClienteResponse;

import java.util.List;

public interface ClienteService {
    ClienteResponse criaCliente(ClienteRequest clienteRequest);
    List<ClienteListResponse> buscaTodosClientes();
}
