package academy.wakanda.delivery.endereco.domain;

import academy.wakanda.delivery.endereco.application.api.EnderecoAlteracaoRequest;
import academy.wakanda.delivery.endereco.application.api.EnderecoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@CompoundIndex(name = "endereco_index", def = "{'rua' : 1, 'numero' : 1}", unique = true)
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
    private Integer numero;
    private String complemento;
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

    public void altera(EnderecoAlteracaoRequest enderecoAlteracaoRequest) {
        this.estado = enderecoAlteracaoRequest.getEstado();
        this.cidade = enderecoAlteracaoRequest.getCidade();
        this.rua = enderecoAlteracaoRequest.getRua();
        this.numero = enderecoAlteracaoRequest.getNumero();
        this.complemento = enderecoAlteracaoRequest.getComplemento();
        this.pontoReferencia = enderecoAlteracaoRequest.getPontoReferencia();
    }
}
