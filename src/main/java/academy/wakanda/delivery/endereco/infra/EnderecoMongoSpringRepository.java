package academy.wakanda.delivery.endereco.infra;

import academy.wakanda.delivery.endereco.domain.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EnderecoMongoSpringRepository extends MongoRepository<Endereco, UUID> {
}
