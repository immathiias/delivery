package academy.wakanda.delivery.cliente.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class Endereco {
    private Integer idEndereco;
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
