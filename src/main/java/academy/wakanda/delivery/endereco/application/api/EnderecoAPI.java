package academy.wakanda.delivery.endereco.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/endereco")
public interface EnderecoAPI {
    @PatchMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    EnderecoResponse postEndereco(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable UUID idCliente, @Valid @RequestBody EnderecoRequest enderecoRequest);
}
