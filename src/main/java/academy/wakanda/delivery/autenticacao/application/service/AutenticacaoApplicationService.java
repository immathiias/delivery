package academy.wakanda.delivery.autenticacao.application.service;

import academy.wakanda.delivery.autenticacao.domain.Token;
import academy.wakanda.delivery.config.security.service.TokenService;
import academy.wakanda.delivery.credencial.application.service.CredencialService;
import academy.wakanda.delivery.credencial.domain.Credencial;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AutenticacaoApplicationService implements AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final CredencialService credencialService;

    @Override
    public Token autentica(UsernamePasswordAuthenticationToken userCredentials) {
        log.info("[inicia] AutenticacaoApplicationService - autentica");
        var auth = authenticationManager.authenticate(userCredentials);
        Token token = Token.builder()
                .tipo("Bearer")
                .token(tokenService.gerarToken((Credencial) auth.getPrincipal()))
                .build();
        log.info("[finaliza] AutenticacaoApplicationService - autentica");
        return token;
    }

    @Override
    public Token reativaToken(String tokenExpirado) {
        log.info("[inicia] AutenticacaoApplicationService - reativaToken");
        var email = extraiUsuario(tokenExpirado);
        Credencial credencial = credencialService.buscaCredencialPorEmail(email);
        log.info("[finaliza] AutenticacaoApplicationService - reativaToken");
        return Token.builder().tipo("Bearer")
                .token(tokenService.gerarToken(credencial))
                .build();
    }

    private String extraiUsuario(String tokenExpirado){
        return tokenService.getSubject(tokenExpirado);
    }

}
