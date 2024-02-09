package academy.wakanda.delivery.pedido.application.api;

import academy.wakanda.delivery.cliente.domain.Endereco;
import academy.wakanda.delivery.pedido.domain.Entrega;
import academy.wakanda.delivery.pedido.domain.Pedido;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class PedidoDetalhadoCliente {
    private UUID idPedido;
    private String produto;
    private String detalhesPedido;
    private Endereco enderecoEntrega;
    private Entrega entrega;
    private LocalDateTime dataHoraDoPedido;

    public PedidoDetalhadoCliente(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.produto = pedido.getProduto();
        this.detalhesPedido = pedido.getDetalhesPedido();
        this.enderecoEntrega = pedido.getEnderecoEntrega();
        this.entrega = pedido.getEntrega();
        this.dataHoraDoPedido = pedido.getDataHoraDoPedido();
    }
}
