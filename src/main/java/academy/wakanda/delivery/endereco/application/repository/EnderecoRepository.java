package academy.wakanda.delivery.endereco.application.repository;

import academy.wakanda.delivery.endereco.application.api.EnderecoRequest;
import academy.wakanda.delivery.endereco.domain.Endereco;

import java.util.UUID;

public interface EnderecoRepository {
    Endereco criaEndereco(Endereco endereco);
}
