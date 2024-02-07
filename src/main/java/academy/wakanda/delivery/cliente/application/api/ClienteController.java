package academy.wakanda.delivery.cliente.application.api;

import academy.wakanda.delivery.cliente.application.service.ClienteService;
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
public class ClienteController implements ClienteAPI {
    private final ClienteService clienteService;

    @Override
    public ClienteResponse postCliente(ClienteRequest clienteRequest) {
        log.info("[inicia] ClienteController - postCliente");
        ClienteResponse clienteCriado = clienteService.criaCliente(clienteRequest);
        log.info("[finaliza] ClienteController - postCliente");
        return clienteCriado;
    }

    @Override
    public List<ClienteListResponse> getTodosClientes() {
        log.info("[inicia] ClienteController - getTodosClientes");
        List<ClienteListResponse> clientes = clienteService.buscaTodosClientes();
        log.info("[finaliza] ClienteController - getTodosClientes");
        return clientes;
    }

    @Override
    public ClienteDetalhadoResponse getClientePorId(UUID idCliente) {
        log.info("[inicia] ClienteController - getClientePorId");
        log.info("[idCliente] {}", idCliente);
        ClienteDetalhadoResponse cliente = clienteService.buscaClientePorId(idCliente);
        log.info("[finaliza] ClienteController - getClientePorId");
        return cliente;
    }

    @Override
    public void patchClientePorId(String token, UUID idCliente, ClienteAlteracaoRequest clienteAlteracaoRequest) {
        log.info("[inicia] ClienteController - patchClientePorId");
        log.info("[idCliente] {}", idCliente);
        clienteService.atualizaClientePorId(token, idCliente, clienteAlteracaoRequest);
        log.info("[finaliza] ClienteController - patchClientePorId");
    }

    @Override
    public void postEndereco(UUID idCliente, EnderecoRequest enderecoRequest) {
        log.info("[inicia] ClienteController - postCliente");
        clienteService.adicionaEnderecoCliente(idCliente, enderecoRequest);
        log.info("[finaliza] ClienteController - postCliente");
    }

    @Override
    public void deleteClientePorId(String token, UUID idCliente) {
        log.info("[inicia] ClienteController - deleteClientePorId");
        clienteService.deletaClientePorId(token, idCliente);
        log.info("[finaliza] ClienteController - deleteClientePorId");
    }
}
