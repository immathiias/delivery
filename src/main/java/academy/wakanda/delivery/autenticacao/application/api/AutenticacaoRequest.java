package academy.wakanda.delivery.autenticacao.application.api;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutenticacaoRequest {
    @NotBlank(message = "O email não pode estar em branco!")
    @Email
    private String email;
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @NotNull
    private String senha;

    public UsernamePasswordAuthenticationToken getUserPassToken() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}