package academy.wakanda.delivery.cliente.application.service;

import academy.wakanda.delivery.cliente.application.api.*;
import academy.wakanda.delivery.cliente.application.repository.ClienteRepository;
import academy.wakanda.delivery.cliente.domain.Cliente;
import academy.wakanda.delivery.cliente.domain.Endereco;
import academy.wakanda.delivery.config.security.service.TokenService;
import academy.wakanda.delivery.credencial.application.service.CredencialService;
import academy.wakanda.delivery.handler.APIException;
import academy.wakanda.delivery.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClienteApplicationService implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final CredencialService credencialService;
    private final TokenService tokenService;

    @Override
    public ClienteResponse criaCliente(ClienteRequest clienteRequest) {
        log.info("[inicia] ClienteApplicationService - criaCliente");
        credencialService.criaNovaCredencial(clienteRequest);
        Cliente cliente = clienteRepository.salva(new Cliente(clienteRequest));
        log.info("[finaliza] ClienteApplicationService - criaCliente");
        return ClienteResponse.builder()
                .idCliente(cliente.getIdCliente())
                .build();
    }

    @Override
    public List<ClienteListResponse> buscaTodosClientes() {
        log.info("[inicia] ClienteApplicationService - buscaTodosClientes");
        List<Cliente> clientes = clienteRepository.buscaTodosClientes();
        log.info("[finaliza] ClienteApplicationService - buscaTodosClientes");
        return ClienteListResponse.converte(clientes);
    }

    @Override
    public ClienteDetalhadoResponse buscaClientePorId(UUID idCliente) {
        log.info("[inicia] ClienteApplicationService - buscaClientePorId");
        Cliente cliente = clienteRepository.buscaClientePorId(idCliente);
        log.info("[finaliza] ClienteApplicationService - buscaClientePorId");
        return new ClienteDetalhadoResponse(cliente);
    }

    @Override
    public void atualizaClientePorId(String token, UUID idCliente, ClienteAlteracaoRequest clienteAlteracaoRequest) {
        log.info("[inicia] ClienteApplicationService - atualizaClientePorId");
        String clienteEmail = tokenService.validarToken(token);
        Cliente cliente = clienteRepository.buscaClientePorEmail(clienteEmail);
        cliente.validaCliente(idCliente);
        cliente.altera(clienteAlteracaoRequest);
        clienteRepository.salva(cliente);
        log.info("[finaliza] ClienteApplicationService - atualizaClientePorId");
    }

    @Override
    public void adicionaEnderecoCliente(UUID idCliente, EnderecoRequest enderecoRequest) {
        log.info("[inicia] ClienteApplicationService - adicionaEnderecoEPedidoCliente");
        Cliente cliente = clienteRepository.buscaClientePorId(idCliente);
        cliente.adicionaEndereco(new Endereco(enderecoRequest));
        clienteRepository.salva(cliente);
        log.info("[finaliza] ClienteApplicationService - adicionaEnderecoEPedidoCliente");
    }

    @Override
    public void adicionaEnderecoEPedidoCliente(Cliente cliente, Pedido pedido, EnderecoRequest enderecoRequest) {
        log.info("[inicia] ClienteApplicationService - adicionaEnderecoEPedidoCliente");
        cliente.adicionaEndereco(new Endereco(enderecoRequest));
        cliente.adicionaPedido(pedido);
        clienteRepository.salva(cliente);
        log.info("[finaliza] ClienteApplicationService - adicionaEnderecoEPedidoCliente");
    }

    @Override
    public void deletaClientePorId(String token, UUID idCliente) {
        log.info("[inicia] ClienteApplicationService - deletaClientePorId");
        String clienteEmail = tokenService.getEmailByBearerToken(token)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Cliente não encontrado!"));
        Cliente cliente = clienteRepository.buscaClientePorEmail(clienteEmail);
        cliente.validaCliente(idCliente);
        clienteRepository.deletaCliente(cliente);
        log.info("[finaliza] ClienteApplicationService - deletaClientePorId");
    }
}
