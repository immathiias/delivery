package academy.wakanda.delivery.config.security.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import academy.wakanda.delivery.credencial.domain.Credencial;
import academy.wakanda.delivery.handler.APIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        return gerarToken((Credencial) authentication.getPrincipal());
    }

    public String gerarToken(Credencial credencial) {
        try {
            log.info("[inicia] TokenService - criação de token");
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("api-delivery").withSubject(credencial.getUsername())
                    .withExpiresAt(genExpirationDate()).sign(algorithm);
            log.info("[finaliza] TokenService - criação de token");
            return token;
        } catch (JWTCreationException ex) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Erro ao gerar o token.");
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String tokenExtraido = token.substring(7, token.length());
            return JWT.require(algorithm)
                    .withIssuer("api-delivery")
                    .build()
                    .verify(tokenExtraido)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw APIException.build(HttpStatus.FORBIDDEN, "O Token enviado está inválido. Tente novamente.");
        }
    }

    public String getSubject(String token){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api-delivery")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            e.printStackTrace();
            throw APIException.build(HttpStatus.FORBIDDEN, "O token está inválido ou expirado.");
        }
    }

    public Optional<String> getEmailByBearerToken(String bearerToken) {
        log.info("[inicia] TokenService - getEmailByBearerToken");
        String token = bearerToken.substring(7, bearerToken.length());
        log.info(token);
        log.info("[finaliza] TokenService - getEmailByBearerToken");
        return Optional.ofNullable(this.getSubject(token));
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}