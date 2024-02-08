package academy.wakanda.delivery.pedido.infra;

import academy.wakanda.delivery.handler.APIException;
import academy.wakanda.delivery.pedido.application.api.PedidoRequest;
import academy.wakanda.delivery.pedido.application.repository.PedidoRepository;
import academy.wakanda.delivery.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PedidoInfraRepository implements PedidoRepository {
    private final PedidoMongoSpringRepository pedidoMongoSpringRepository;

    @Override
    public Pedido salvaPedido(Pedido pedido) {
        log.info("[inicia] PedidoInfraRepository - salvaPedido");
        try {
            pedidoMongoSpringRepository.save(pedido);
        } catch (DataIntegrityViolationException e) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Existem dados duplicados.");
        }
        log.info("[finaliza] PedidoInfraRepository - salvaPedido");
        return pedido;
    }

    @Override
    public List<Pedido> buscaTodosPedidosDoCliente(UUID idCliente) {
        log.info("[inicia] PedidoInfraRepository - buscaTodosPedidosDoCliente");
        List<Pedido> pedidosDoCliente = pedidoMongoSpringRepository.findAllByIdCliente(idCliente);
        log.info("[finaliza] PedidoInfraRepository - buscaTodosPedidosDoCliente");
        return pedidosDoCliente;
    }
}
