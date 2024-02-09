package academy.wakanda.delivery.endereco.application.api;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class EnderecoResponse {
    private UUID idEndereco;
}
