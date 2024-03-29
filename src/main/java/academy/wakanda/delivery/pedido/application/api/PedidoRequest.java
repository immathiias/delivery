package academy.wakanda.delivery.pedido.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class PedidoRequest {
    @NotBlank
    private String produto;
    @NotBlank
    private String detalhesPedido;
    @NotNull
    private UUID idEnderecoEntrega;
}
