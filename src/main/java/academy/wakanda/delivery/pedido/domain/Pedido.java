package academy.wakanda.delivery.pedido.domain;

import academy.wakanda.delivery.handler.APIException;
import academy.wakanda.delivery.pedido.application.api.PedidoAlteracaoRequest;
import academy.wakanda.delivery.pedido.application.api.PedidoRequest;
import academy.wakanda.delivery.pedido.application.api.PedidoRequestCriandoEndereco;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

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
    private UUID idEnderecoEntrega;
    @NotBlank
    private String produto;
    @NotBlank
    private String detalhesPedido;
    @NotBlank
    private Entrega entrega;
    private LocalDateTime dataHoraDoPedido;
    private LocalDateTime dataHoraAlteracaoDoPedido;

    public Pedido(UUID idCliente, PedidoRequestCriandoEndereco pedidoRequest, UUID idEnderecoEntrega) {
        this.idPedido = UUID.randomUUID();
        this.idCliente = idCliente;
        this.idEnderecoEntrega = idEnderecoEntrega;
        this.produto = pedidoRequest.getProduto();
        this.detalhesPedido = pedidoRequest.getDetalhesPedido();
        this.entrega = new Entrega(false, null);
        this.dataHoraDoPedido = LocalDateTime.now();
    }

    public Pedido(UUID idCliente, PedidoRequest pedidoRequest) {
        this.idPedido = UUID.randomUUID();
        this.idCliente = idCliente;
        this.idEnderecoEntrega = pedidoRequest.getIdEnderecoEntrega();
        this.produto = pedidoRequest.getProduto();
        this.detalhesPedido = pedidoRequest.getDetalhesPedido();
        this.entrega = new Entrega(false, null);
        this.dataHoraDoPedido = LocalDateTime.now();
    }

    public void altera(PedidoAlteracaoRequest pedidoAlteracaoRequest) {
        this.produto = pedidoAlteracaoRequest.getProduto();
        this.detalhesPedido = pedidoAlteracaoRequest.getDetalhesPedido();
        this.dataHoraAlteracaoDoPedido = LocalDateTime.now();
    }

    public void realizaEntrega() {
        if (checaEntrega()) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "O pedido já foi entregue.");
        }
        this.entrega.setPedidoEntregue(true);
        this.entrega.setDataHoraDaEntrega(LocalDateTime.now());
    }
    public void retiraEntrega() {
        if (checaEntrega()) {
            this.entrega.setPedidoEntregue(false);
            this.entrega.setDataHoraDaEntrega(null);
        }
    }

    public Boolean checaEntrega() {
       return this.entrega.getPedidoEntregue();
    }

}
