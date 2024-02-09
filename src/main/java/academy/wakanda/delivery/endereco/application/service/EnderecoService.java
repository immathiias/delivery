package academy.wakanda.delivery.endereco.application.service;

import academy.wakanda.delivery.endereco.application.api.EnderecoDetalhadoResponse;
import academy.wakanda.delivery.endereco.application.api.EnderecoListResponse;
import academy.wakanda.delivery.endereco.application.api.EnderecoRequest;
import academy.wakanda.delivery.endereco.application.api.EnderecoResponse;

import java.util.List;
import java.util.UUID;

public interface EnderecoService {
    EnderecoResponse adicionaEnderecoCliente(UUID idCliente, EnderecoRequest enderecoRequest);
    List<EnderecoListResponse> buscaTodosEnderecosDoCliente(String token, UUID idCliente);
    EnderecoDetalhadoResponse buscaEnderecoDoClientePorId(String token, UUID idCliente, UUID idEndereco);
}
