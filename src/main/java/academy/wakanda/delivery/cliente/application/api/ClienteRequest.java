package academy.wakanda.delivery.cliente.application.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class ClienteRequest {
    @NotBlank
    private String nome;
    @Email
    private String email;
    @Size(min = 6)
    private String senha;
    @NotBlank
    private String telefone;
}
