package academy.wakanda.delivery.pedido.application.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.UUID;

@Value
public class PedidoRequest {
    @NotBlank
    private String produto;
    @NotBlank
    private String detalhesPedido;
    @NotBlank
    private UUID idEnderecoEntrega;
}
