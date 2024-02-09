package academy.wakanda.delivery.endereco.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class EnderecoRequest {
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String rua;
    @NotNull
    private Integer numero;
    private String complemento;
    private String pontoReferencia;
}
