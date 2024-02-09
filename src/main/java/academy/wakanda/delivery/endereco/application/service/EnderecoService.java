package academy.wakanda.delivery.endereco.application.service;

import academy.wakanda.delivery.endereco.application.api.EnderecoRequest;
import academy.wakanda.delivery.endereco.application.api.EnderecoResponse;

import java.util.UUID;

public interface EnderecoService {
    EnderecoResponse adicionaEnderecoCliente(UUID idCliente, EnderecoRequest enderecoRequest);
}
