package academy.wakanda.delivery.endereco.infra;

import academy.wakanda.delivery.endereco.domain.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface EnderecoMongoSpringRepository extends MongoRepository<Endereco, UUID> {
    List<Endereco> findAllByIdCliente(UUID idCliente);
    boolean existsByRuaAndNumero(String rua, Integer numero);
}
