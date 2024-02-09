package academy.wakanda.delivery.endereco.application.repository;

import academy.wakanda.delivery.endereco.application.api.EnderecoRequest;
import academy.wakanda.delivery.endereco.domain.Endereco;

import java.util.List;
import java.util.UUID;

public interface EnderecoRepository {
    Endereco salvaEndereco(Endereco endereco);
    List<Endereco> buscaTodosEnderecosDoCliente(UUID idCliente);
    Endereco buscaEnderecoDoClientePorId(UUID idCliente, UUID idEndereco);
}
