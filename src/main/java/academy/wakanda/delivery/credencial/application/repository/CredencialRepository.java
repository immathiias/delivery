package academy.wakanda.delivery.credencial.application.repository;

import academy.wakanda.delivery.credencial.domain.Credencial;

public interface CredencialRepository {
    void salva(Credencial novaCredencial);
    Credencial buscaCredencialPorEmail(String email);
}
