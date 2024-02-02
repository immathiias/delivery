package academy.wakanda.delivery.credencial.application.repository;

import academy.wakanda.delivery.credencial.domain.Credencial;

public interface CredencialRepository {
    Credencial salva(Credencial credencial);
    Credencial buscaCredencialPorEmail(String email);
}
