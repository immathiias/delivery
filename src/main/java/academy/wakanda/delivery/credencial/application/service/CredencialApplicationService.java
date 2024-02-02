package academy.wakanda.delivery.credencial.application.service;

import academy.wakanda.delivery.cliente.application.api.ClienteRequest;
import academy.wakanda.delivery.credencial.application.repository.CredencialRepository;
import academy.wakanda.delivery.credencial.domain.Credencial;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CredencialApplicationService implements CredencialService {
    private final CredencialRepository credencialRepository;
    @Override
    public void criaNovaCredencial(ClienteRequest clienteRequest) {
        log.info("[inicia] CredencialApplicationService - criaNovaCredencial");
        var novaCredencial = new Credencial(clienteRequest.getEmail(), clienteRequest.getSenha());
        credencialRepository.salva(novaCredencial);
        log.info("[finaliza] CredencialApplicationService - criaNovaCredencial");
    }

    @Override
    public Credencial buscaCredencialPorEmail(String email) {
        log.info("[inicia] CredencialApplicationService - buscaCredencialPorEmail");
        Credencial credencial = credencialRepository.buscaCredencialPorEmail(email);
        log.info("[finaliza] CredencialApplicationService - buscaCredencialPorEmail");
        return credencial;
    }
}
