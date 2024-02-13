package academy.wakanda.delivery.endereco.application.service;

import academy.wakanda.delivery.cliente.application.service.ClienteService;
import academy.wakanda.delivery.endereco.application.api.*;
import academy.wakanda.delivery.endereco.application.repository.EnderecoRepository;
import academy.wakanda.delivery.endereco.application.service.EnderecoService;
import academy.wakanda.delivery.endereco.domain.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class EnderecoApplicationService implements EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final ClienteService clienteService;

    @Override
    public EnderecoResponse adicionaEnderecoCliente(UUID idCliente, EnderecoRequest enderecoRequest) {
        log.info("[inicia] EnderecoApplicationService - adicionaEnderecoCliente");
        Endereco endereco = enderecoRepository.salvaEndereco(new Endereco(idCliente, enderecoRequest));
        log.info("[finaliza] EnderecoApplicationService - adicionaEnderecoCliente");
        return EnderecoResponse.builder()
                .idEndereco(endereco.getIdEndereco())
                .build();
    }

    @Override
    public List<EnderecoListResponse> buscaTodosEnderecosDoCliente(String token, UUID idCliente) {
        log.info("[inicia] EnderecoApplicationService - buscaTodosEnderecosDoCliente");
        clienteService.checaCliente(token, idCliente);
        List<Endereco> enderecos = enderecoRepository.buscaTodosEnderecosDoCliente(idCliente);
        log.info("[finaliza] EnderecoApplicationService - buscaTodosEnderecosDoCliente");
        return EnderecoListResponse.converte(enderecos);
    }

    @Override
    public EnderecoDetalhadoResponse buscaEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco) {
        log.info("[inicia] EnderecoApplicationService - buscaEnderecoDoClientePorId");
        clienteService.checaCliente(token, idCliente);
        Endereco endereco = enderecoRepository.buscaEnderecoDoClientePorId(idCliente, idEndereco);
        log.info("[finaliza] EnderecoApplicationService - buscaEnderecoDoClientePorId");
        return new EnderecoDetalhadoResponse(endereco);
    }

    @Override
    public void alteraEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco, EnderecoAlteracaoRequest enderecoAlteracaoRequest) {
        log.info("[inicia] EnderecoApplicationService - alteraEnderecoDoClientePorId");
        clienteService.checaCliente(token, idCliente);
        Endereco endereco = enderecoRepository.buscaEnderecoDoClientePorId(idCliente, idEndereco);
        endereco.altera(enderecoAlteracaoRequest);
        enderecoRepository.salvaEndereco(endereco);
        log.info("[finaliza] EnderecoApplicationService - alteraEnderecoDoClientePorId");
    }

    @Override
    public void deletaEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco) {
        log.info("[inicia] EnderecoApplicationService - deletaEnderecoDoClientePorId");
        clienteService.checaCliente(token, idCliente);
        Endereco endereco = enderecoRepository.buscaEnderecoDoClientePorId(idCliente, idEndereco);
        enderecoRepository.deletaEndereco(endereco);
        log.info("[finaliza] EnderecoApplicationService - deletaEnderecoDoClientePorId");
    }
}
