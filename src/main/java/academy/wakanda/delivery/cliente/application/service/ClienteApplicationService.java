package academy.wakanda.delivery.cliente.application.service;

import academy.wakanda.delivery.cliente.application.api.ClienteListResponse;
import academy.wakanda.delivery.cliente.application.api.ClienteRequest;
import academy.wakanda.delivery.cliente.application.api.ClienteResponse;
import academy.wakanda.delivery.cliente.application.repository.ClienteRepository;
import academy.wakanda.delivery.cliente.domain.Cliente;
import academy.wakanda.delivery.credencial.application.service.CredencialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClienteApplicationService implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final CredencialService credencialService;

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
}
