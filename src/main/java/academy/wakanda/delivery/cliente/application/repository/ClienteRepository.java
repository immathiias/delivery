package academy.wakanda.delivery.cliente.application.repository;

import academy.wakanda.delivery.cliente.domain.Cliente;

import java.util.List;

public interface ClienteRepository {
    Cliente salva(Cliente cliente);
    List<Cliente> buscaTodosClientes();
}
