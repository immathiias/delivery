package academy.wakanda.delivery.cliente.application.api;

import academy.wakanda.delivery.cliente.domain.Cliente;
import academy.wakanda.delivery.pedido.domain.Pedido;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value
public class ClienteDetalhadoResponse {
    private UUID idCliente;
    private String email;
    private String nome;
    private String telefone;
    private LocalDateTime dataHoraDoCadastro;
    private LocalDateTime dataHoraUltimaAlteracao;

    public ClienteDetalhadoResponse(Cliente cliente, List<Pedido> pedidos) {
        this.idCliente = cliente.getIdCliente();
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.dataHoraDoCadastro = cliente.getDataHoraDoCadastro();
        this.dataHoraUltimaAlteracao = cliente.getDataHoraUltimaAlteracao();
    }
}
