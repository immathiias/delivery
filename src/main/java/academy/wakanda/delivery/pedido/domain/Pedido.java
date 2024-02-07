package academy.wakanda.delivery.pedido.domain;

import academy.wakanda.delivery.cliente.domain.Endereco;
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
    private UUID idCliente;
    private String produto;
    private String detalhesPedido;
    private Endereco idEnderecoEntrega;

    private LocalDateTime dataHoraCriacaoDoPedido;
    private LocalDateTime dataHoraAtuzaliacaoDoPedido;

}
