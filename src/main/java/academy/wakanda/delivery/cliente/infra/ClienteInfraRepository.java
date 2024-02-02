package academy.wakanda.delivery.cliente.infra;

import academy.wakanda.delivery.cliente.application.repository.ClienteRepository;
import academy.wakanda.delivery.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ClienteInfraRepository implements ClienteRepository {
    private final ClienteMongoSpringRepository clienteMongoSpringRepository;

    @Override
    public Cliente salva(Cliente cliente) {
        log.info("[inicia] ClienteInfraRepository - salva");
        try {
            clienteMongoSpringRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Existem dados duplicados.");
        }
        log.info("[finaliza] ClienteInfraRepository - salva");
        return cliente;
    }
}
