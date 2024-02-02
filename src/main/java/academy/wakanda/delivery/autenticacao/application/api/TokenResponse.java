package academy.wakanda.delivery.autenticacao.application.api;

import academy.wakanda.delivery.autenticacao.domain.Token;
import lombok.Value;

@Value
public class TokenResponse {
    private String token;
    private String tipo;

    public TokenResponse(Token token) {
        this.token = token.getToken();
        this.tipo = token.getTipo();
    }

}
