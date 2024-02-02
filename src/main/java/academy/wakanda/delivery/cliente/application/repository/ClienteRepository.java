package academy.wakanda.delivery.cliente.application.repository;

import academy.wakanda.delivery.cliente.domain.Cliente;

public interface ClienteRepository {
    Cliente salva(Cliente cliente);
}
