package academy.wakanda.delivery.endereco.application.service;

import academy.wakanda.delivery.endereco.application.api.*;

import java.util.List;
import java.util.UUID;

public interface EnderecoService {
    EnderecoResponse adicionaEnderecoCliente(UUID idCliente, EnderecoRequest enderecoRequest);
    List<EnderecoListResponse> buscaTodosEnderecosDoCliente(String token, UUID idCliente);
    EnderecoDetalhadoResponse buscaEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco);
    void alteraEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco, EnderecoAlteracaoRequest enderecoAlteracaoRequest);
}
