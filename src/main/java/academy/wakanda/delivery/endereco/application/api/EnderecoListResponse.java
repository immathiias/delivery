package academy.wakanda.delivery.endereco.application.api;

import academy.wakanda.delivery.endereco.domain.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class EnderecoListResponse {
    private UUID idEndereco;
    private String estado;
    private String cidade;
    private String rua;
    private Integer numero;
    private String complemento;
    private String pontoReferencia;

    public static List<EnderecoListResponse> converte(List<Endereco> enderecos) {
        return enderecos.stream().map(EnderecoListResponse::new).collect(Collectors.toList());
    }

    public EnderecoListResponse(Endereco endereco) {
        this.idEndereco = endereco.getIdEndereco();
        this.estado = endereco.getEstado();
        this.cidade = endereco.getCidade();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.pontoReferencia = endereco.getPontoReferencia();
    }
}
