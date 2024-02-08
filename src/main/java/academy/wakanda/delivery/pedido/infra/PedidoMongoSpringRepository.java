package academy.wakanda.delivery.pedido.infra;

import academy.wakanda.delivery.pedido.domain.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PedidoMongoSpringRepository extends MongoRepository<Pedido, UUID> {
}
