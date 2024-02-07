package academy.wakanda.delivery.cliente.infra;

import academy.wakanda.delivery.cliente.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteMongoSpringRepository extends MongoRepository<Cliente, UUID> {
    Optional<Cliente> findByEmail(String cliente);
}
