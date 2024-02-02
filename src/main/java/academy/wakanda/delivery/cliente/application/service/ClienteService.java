package academy.wakanda.delivery.cliente.application.service;

import academy.wakanda.delivery.cliente.application.api.ClienteRequest;
import academy.wakanda.delivery.cliente.application.api.ClienteResponse;

public interface ClienteService {
    ClienteResponse criaCliente(ClienteRequest clienteRequest);
}
