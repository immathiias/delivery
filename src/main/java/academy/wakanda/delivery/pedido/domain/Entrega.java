package academy.wakanda.delivery.pedido.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Entrega {
    private Boolean pedidoEntregue;
    private LocalDateTime dataHoraDaEntrega;

    public Entrega(boolean pedidoEntregue, LocalDateTime dataHoraDaEntrega) {
        this.pedidoEntregue = pedidoEntregue;
        this.dataHoraDaEntrega = dataHoraDaEntrega;
    }
}
