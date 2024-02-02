package academy.wakanda.delivery.cliente.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Endereco {
    private UUID idEndereco;
    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private String complemento;
}
