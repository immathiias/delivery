package academy.wakanda.delivery.pedido.application.api;

import academy.wakanda.delivery.cliente.application.api.EnderecoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PedidoRequestCriandoEndereco {
    @NotBlank
    private String produto;
    @NotBlank
    private String detalhesPedido;
    @NotNull
    private EnderecoRequest enderecoEntrega;
}
