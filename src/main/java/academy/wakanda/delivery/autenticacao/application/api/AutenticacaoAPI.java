package academy.wakanda.delivery.autenticacao.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;

@RequestMapping("/public/v1/autenticacao")
public interface AutenticacaoAPI {
    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    TokenResponse autentica(@RequestBody @Valid AutenticacaoRequest autenticacaoRequest) throws AuthenticationException;

    @PostMapping("/reativacao")
    @ResponseStatus(code = HttpStatus.OK)
    TokenResponse reativaAutenticacao(@RequestHeader("Authorization") String tokenExpirado)
            throws AuthenticationException;
}