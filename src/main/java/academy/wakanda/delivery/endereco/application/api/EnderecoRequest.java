package academy.wakanda.delivery.endereco.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.data.mongodb.core.index.Indexed;

@Value
public class EnderecoRequest {
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String rua;
    @NotNull
    @Indexed(unique = true)
    private Integer numero;
    @Indexed(unique = true)
    private String complemento;
    @Indexed(unique = true)
    private String pontoReferencia;
}
