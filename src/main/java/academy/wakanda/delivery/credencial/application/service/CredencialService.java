package academy.wakanda.delivery.credencial.application.service;

import academy.wakanda.delivery.cliente.application.api.ClienteRequest;
import academy.wakanda.delivery.credencial.domain.Credencial;
import jakarta.validation.Valid;

public interface CredencialService {
    void criaNovaCredencial(@Valid ClienteRequest clienteRequest);
    Credencial buscaCredencialPorEmail(String email);
}
