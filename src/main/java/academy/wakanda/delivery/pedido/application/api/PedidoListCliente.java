package academy.wakanda.delivery.pedido.application.api;

import academy.wakanda.delivery.pedido.domain.Pedido;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PedidoListCliente {
    private String produto;
    private String detalhesPedido;
    private LocalDateTime dataHoraCriacaoDoPedido;

    public static List<PedidoListCliente> converte(List<Pedido> pedidosDoCliente) {
        return pedidosDoCliente.stream()
                .map(PedidoListCliente::new)
                .collect(Collectors.toList());
    }

    public PedidoListCliente(Pedido pedido) {
        this.produto = pedido.getProduto();
        this.detalhesPedido = pedido.getDetalhesPedido();
        this.dataHoraCriacaoDoPedido = pedido.getDataHoraDoPedido();
    }
}
