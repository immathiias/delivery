package academy.wakanda.delivery.cliente.domain;

import academy.wakanda.delivery.cliente.application.api.EnderecoRequest;
import academy.wakanda.delivery.cliente.application.repository.ClienteRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Endereco {
    private UUID idEndereco;
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

    public Endereco(EnderecoRequest enderecoRequest) {
        this.idEndereco = UUID.randomUUID();
        this.estado = enderecoRequest.getEstado();
        this.cidade = enderecoRequest.getCidade();
        this.rua = enderecoRequest.getRua();
        this.numero = enderecoRequest.getNumero();
        this.complemento = enderecoRequest.getComplemento();
        this.pontoReferencia = enderecoRequest.getPontoReferencia();
    }
}
