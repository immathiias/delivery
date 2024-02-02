package academy.wakanda.delivery.credencial.infra;

import academy.wakanda.delivery.credencial.application.repository.CredencialRepository;
import academy.wakanda.delivery.credencial.domain.Credencial;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class CredencialInfraRepository implements CredencialRepository {
    private final CredencialMongoSpringRepository credencialMongoSpringRepository;

    @Override
    public Credencial salva(Credencial credencial) {
        log.info("[inicia] CredencialInfraRepository - salva");
        credencialMongoSpringRepository.save(credencial);
        log.info("[finaliza] CredencialInfraRepository - salva");
        return credencial;
    }

    @Override
    public Credencial buscaCredencialPorEmail(String email) {
        log.info("[inicia] CredencialInfraRepository - buscaCredencialPorEmail");
        var credencial = credencialMongoSpringRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Não existe credencial para o Cliente informado!"));
        log.info("[finaliza] CredencialInfraRepository - buscaCredencialPorEmail");
        return null;
    }
}
