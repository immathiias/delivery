package academy.wakanda.delivery.cliente.domain;

import academy.wakanda.delivery.cliente.application.api.ClienteAlteracaoRequest;
import academy.wakanda.delivery.cliente.application.api.ClienteRequest;
import academy.wakanda.delivery.handler.APIException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Cliente")
public class Cliente {
    @Id
    private UUID idCliente;
    @Email
    @NotBlank
    @Indexed(unique = true)
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
    private LocalDateTime dataHoraDoCadastro;
    private LocalDateTime dataHoraUltimaAlteracao;

    public Cliente(ClienteRequest clienteRequest) {
        this.idCliente = UUID.randomUUID();
        this.email = clienteRequest.getEmail();
        this.nome = clienteRequest.getNome();
        this.telefone = clienteRequest.getTelefone();
        this.dataHoraDoCadastro = LocalDateTime.now();
    }

    public void altera(ClienteAlteracaoRequest clienteAlteracaoRequest) {
        this.nome = clienteAlteracaoRequest.getNome();
        this.telefone = clienteAlteracaoRequest.getTelefone();
        this.dataHoraUltimaAlteracao = LocalDateTime.now();
    }
    public void validaCliente(UUID idCliente) {
        if (!this.idCliente.equals(idCliente)) {
            throw APIException.build(HttpStatus.UNAUTHORIZED, "Credencial de autenticação não é válida!");
        }
    }
}
