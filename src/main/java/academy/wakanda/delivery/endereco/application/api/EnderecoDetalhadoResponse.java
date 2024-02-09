package academy.wakanda.delivery.endereco.application.api;

import academy.wakanda.delivery.endereco.domain.Endereco;
import lombok.Value;

import java.util.UUID;

@Value
public class EnderecoDetalhadoResponse {
    private UUID idEndereco;
    private String estado;
    private String cidade;
    private String rua;
    private Integer numero;
    private String complemento;
    private String pontoReferencia;

    public EnderecoDetalhadoResponse(Endereco endereco) {
        this.idEndereco = endereco.getIdEndereco();
        this.estado = endereco.getEstado();
        this.cidade = endereco.getCidade();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.pontoReferencia = endereco.getPontoReferencia();
    }
}
