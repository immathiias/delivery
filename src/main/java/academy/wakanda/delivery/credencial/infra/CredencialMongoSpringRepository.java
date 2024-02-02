package academy.wakanda.delivery.credencial.infra;

import academy.wakanda.delivery.credencial.domain.Credencial;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CredencialMongoSpringRepository extends MongoRepository<Credencial, UUID> {
    Optional<Credencial> findByEmail(String email);
}
