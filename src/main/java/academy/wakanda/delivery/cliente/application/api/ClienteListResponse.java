package academy.wakanda.delivery.cliente.application.api;

import academy.wakanda.delivery.cliente.domain.Cliente;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ClienteListResponse {
    private String nome;
    private String telefone;

    public static List<ClienteListResponse> converte(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteListResponse::new)
                .collect(Collectors.toList());
    }

    public ClienteListResponse(Cliente cliente) {
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
    }
}
