package academy.wakanda.delivery.autenticacao.application.api;

import academy.wakanda.delivery.autenticacao.application.service.AutenticacaoService;
import academy.wakanda.delivery.config.security.domain.ValidaConteudoAuthorizationHeader;
import org.springframework.security.core.AuthenticationException;

import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AutenticacaoController implements AutenticacaoAPI {

    private final AutenticacaoService autenticacaoService;

    @Override
    public TokenResponse autentica(AutenticacaoRequest autenticacaoRequest) {
        log.info("[inicia] AutenticacaoController - autentica");
        var token = autenticacaoService.autentica(autenticacaoRequest.getUserPassToken());
        log.info("[finaliza] AutenticacaoController - autentica");
        return new TokenResponse(token);
    }

    @Override
    public TokenResponse reativaAutenticacao(String tokenExpirado) throws AuthenticationException {
        log.info("[inicia] AutenticacaoController - reativaAutenticacao");
        String tokenExpiradoValido = validaTokenExpirado(Optional.of(tokenExpirado));
        var token = autenticacaoService.reativaToken(tokenExpiradoValido);
        log.info("[finaliza] AutenticacaoController - reativaAutenticacao");
        return new TokenResponse(token);
    }

    private String validaTokenExpirado(Optional<String> tokenExpirado) {
        String tokenExp = tokenExpirado.filter(new ValidaConteudoAuthorizationHeader())
                .orElseThrow(() -> new RuntimeException("Token Invalido!"));
        return tokenExp.substring(7, tokenExp.length());
    }

}
