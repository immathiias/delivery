package academy.wakanda.delivery.cliente.infra;

import academy.wakanda.delivery.cliente.application.repository.ClienteRepository;
import academy.wakanda.delivery.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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

    @Override
    public List<Cliente> buscaTodosClientes() {
        log.info("[inicia] ClienteInfraRepository - buscaTodosClientes");
        List<Cliente> clientes = clienteMongoSpringRepository.findAll();
        log.info("[finaliza] ClienteInfraRepository - buscaTodosClientes");
        return clientes;
    }

    @Override
    public Cliente buscaClientePorId(UUID idCliente) {
        log.info("[inicia] ClienteInfraRepository - buscaClientePorId");
        Cliente cliente = clienteMongoSpringRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        log.info("[finaliza] ClienteInfraRepository - buscaClientePorId");
        return cliente;
    }

    @Override
    public Cliente buscaClientePorEmail(String clienteEmail) {
        log.info("[inicia] ClienteInfraRepository - buscaClientePorEmail");
        Cliente cliente = clienteMongoSpringRepository.findByEmail(clienteEmail)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        log.info("[finaliza] ClienteInfraRepository - buscaClientePorEmail");
        return cliente;
    }

    @Override
    public void deletaCliente(Cliente cliente) {
        log.info("[inicia] ClienteInfraRepository - deletaCliente");
        clienteMongoSpringRepository.delete(cliente);
        log.info("[finaliza] ClienteInfraRepository - deletaCliente");
    }
}
