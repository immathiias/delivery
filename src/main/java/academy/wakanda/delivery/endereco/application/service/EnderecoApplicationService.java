package academy.wakanda.delivery.endereco.application.service;

import academy.wakanda.delivery.endereco.application.api.EnderecoRequest;
import academy.wakanda.delivery.endereco.application.api.EnderecoResponse;
import academy.wakanda.delivery.endereco.application.repository.EnderecoRepository;
import academy.wakanda.delivery.endereco.application.service.EnderecoService;
import academy.wakanda.delivery.endereco.domain.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class EnderecoApplicationService implements EnderecoService {
    private final EnderecoRepository enderecoRepository;

    @Override
    public EnderecoResponse adicionaEnderecoCliente(UUID idCliente, EnderecoRequest enderecoRequest) {
        log.info("[inicia] EnderecoApplicationService - adicionaEnderecoCliente");
        Endereco endereco = enderecoRepository.criaEndereco(new Endereco(idCliente, enderecoRequest));
        log.info("[finaliza] EnderecoApplicationService - adicionaEnderecoCliente");
        return EnderecoResponse.builder()
                .idEndereco(endereco.getIdEndereco())
                .build();
    }
}
