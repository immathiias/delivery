package academy.wakanda.delivery.endereco.infra;

import academy.wakanda.delivery.endereco.application.api.EnderecoRequest;
import academy.wakanda.delivery.endereco.application.repository.EnderecoRepository;
import academy.wakanda.delivery.endereco.domain.Endereco;
import academy.wakanda.delivery.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

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
            throw APIException.build(HttpStatus.BAD_REQUEST, "Existem dados duplicados.");
        }
        log.info("[finaliza] EnderecoInfraRepository criaEndereco");
        return endereco;
    }
}
