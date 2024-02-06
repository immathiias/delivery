package academy.wakanda.delivery.cliente.application.repository;

import academy.wakanda.delivery.cliente.domain.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository {
    Cliente salva(Cliente cliente);
    List<Cliente> buscaTodosClientes();
    Cliente buscaClientePorId(UUID idCliente);
}
