package academy.wakanda.delivery.endereco.application.api;

import academy.wakanda.delivery.cliente.application.service.ClienteService;
import academy.wakanda.delivery.endereco.application.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @Override
    public List<EnderecoListResponse> getTodosEnderecoDoCliente(String token, UUID idCliente) {
        log.info("[inicia] EnderecoController - getTodosEnderecoDoCliente");
        List<EnderecoListResponse> enderecos = enderecoService.buscaTodosEnderecosDoCliente(token, idCliente);
        log.info("[finaliza] EnderecoController - getTodosEnderecoDoCliente");
        return enderecos;
    }

    @Override
    public EnderecoDetalhadoResponse getEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco) {
        log.info("[inicia] EnderecoController - getEnderecoDoClientePorId");
        EnderecoDetalhadoResponse endereco = enderecoService.buscaEnderecoDoClientePorId(token, idCliente, idEndereco);
        log.info("[finaliza] EnderecoController - getEnderecoDoClientePorId");
        return endereco;
    }

    @Override
    public void patchEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco, EnderecoAlteracaoRequest enderecoAlteracaoRequest) {
        log.info("[inicia] EnderecoController - patchEnderecoDoClientePorId");
        enderecoService.alteraEnderecoDoClientePorId(token, idCliente, idEndereco, enderecoAlteracaoRequest);
        log.info("[finaliza] EnderecoController - patchEnderecoDoClientePorId");
    }

    @Override
    public void deleteEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco) {
        log.info("[inicia] EnderecoController - deleteEnderecoDoClientePorId");
        enderecoService.deletaEnderecoDoClientePorId(token, idCliente, idEndereco);
        log.info("[finaliza] EnderecoController - deleteEnderecoDoClientePorId");
    }
}
