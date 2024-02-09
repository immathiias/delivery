package academy.wakanda.delivery.endereco.application.api;

import academy.wakanda.delivery.cliente.application.service.ClienteService;
import academy.wakanda.delivery.endereco.application.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@Log4j2
@RequiredArgsConstructor
public class EnderecoController implements EnderecoAPI {
    private final EnderecoService enderecoService;
    private final ClienteService clienteService;


    @Override
    public EnderecoResponse postEndereco(String token, UUID idCliente, EnderecoRequest enderecoRequest) {
        log.info("[inicia] EnderecoController - postEndereco");
        clienteService.checaCliente(token, idCliente);
        EnderecoResponse endereco = enderecoService.adicionaEnderecoCliente(idCliente, enderecoRequest);;
        log.info("[finaliza] EnderecoController - postEndereco");
        return endereco;
    }
}
