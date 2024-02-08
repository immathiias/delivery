package academy.wakanda.delivery.pedido.domain;

import academy.wakanda.delivery.cliente.domain.Endereco;
import academy.wakanda.delivery.pedido.application.api.PedidoRequest;
import academy.wakanda.delivery.pedido.application.api.PedidoRequestCriandoEndereco;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Pedido")
public class Pedido {
    @Id
    private UUID idPedido;
    @NotBlank
    private UUID idCliente;
    @NotBlank
    private String produto;
    @NotBlank
    private String detalhesPedido;
    @NotBlank
    private Endereco enderecoEntrega;

    private LocalDateTime dataHoraDoPedido;
    private LocalDateTime dataHoraAtuzaliacaoDoPedido;

    public Pedido(UUID idCliente, PedidoRequestCriandoEndereco pedidoRequest, Endereco endereco) {
        this.idPedido = UUID.randomUUID();
        this.idCliente = idCliente;
        this.produto = pedidoRequest.getProduto();
        this.detalhesPedido = pedidoRequest.getDetalhesPedido();
        this.enderecoEntrega = endereco;

        this.dataHoraDoPedido = LocalDateTime.now();
    }
}
