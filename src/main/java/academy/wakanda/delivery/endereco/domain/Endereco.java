package academy.wakanda.delivery.endereco.domain;

import academy.wakanda.delivery.endereco.application.api.EnderecoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Endereco")
public class Endereco {
    @Id
    private UUID idEndereco;
    @NotBlank
    private UUID idCliente;
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

    public Endereco(UUID idCliente, EnderecoRequest enderecoRequest) {
        this.idEndereco = UUID.randomUUID();
        this.idCliente = idCliente;
        this.estado = enderecoRequest.getEstado();
        this.cidade = enderecoRequest.getCidade();
        this.rua = enderecoRequest.getRua();
        this.numero = enderecoRequest.getNumero();
        this.complemento = enderecoRequest.getComplemento();
        this.pontoReferencia = enderecoRequest.getPontoReferencia();
    }
}
