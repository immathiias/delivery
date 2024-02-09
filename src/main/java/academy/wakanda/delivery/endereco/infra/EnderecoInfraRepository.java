package academy.wakanda.delivery.endereco.infra;

import academy.wakanda.delivery.endereco.application.repository.EnderecoRepository;
import academy.wakanda.delivery.endereco.domain.Endereco;
import academy.wakanda.delivery.handler.APIException;
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
public class EnderecoInfraRepository implements EnderecoRepository {
    private final EnderecoMongoSpringRepository enderecoMongoSpringRepository;

    @Override
    public Endereco criaEndereco(Endereco endereco) {
        log.info("[inicia] EnderecoInfraRepository criaEndereco");
        try {
            enderecoMongoSpringRepository.save(endereco);
        } catch (DataIntegrityViolationException e) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Já existe um endereço com a mesma rua e número.");
        }
        log.info("[finaliza] EnderecoInfraRepository criaEndereco");
        return endereco;
    }

    @Override
    public List<Endereco> buscaTodosEnderecosDoCliente(UUID idCliente) {
        log.info("[inicia] EnderecoInfraRepository - buscaTodosEnderecosDoCliente");
        List<Endereco> enderecosDoCliente = enderecoMongoSpringRepository.findAllByIdCliente(idCliente);
        log.info("[finaliza] EnderecoInfraRepository - buscaTodosEnderecosDoCliente");
        return enderecosDoCliente;
    }

    @Override
    public Endereco buscaEnderecoDoClientePorId(UUID idCliente, UUID idEndereco) {
        log.info("[inicia] EnderecoInfraRepository - buscaEnderecoDoClientePorId");
        Endereco endereco = enderecoMongoSpringRepository.findByIdClienteAndIdEndereco(idCliente, idEndereco)
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Endereço não encontrado."));
        log.info("[finaliza] EnderecoInfraRepository - buscaEnderecoDoClientePorId");
        return endereco;
    }
}
