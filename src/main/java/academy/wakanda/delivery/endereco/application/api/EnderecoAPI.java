package academy.wakanda.delivery.endereco.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/endereco")
public interface EnderecoAPI {
    @PostMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.CREATED)
    EnderecoResponse postEndereco(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente,
            @Valid @RequestBody EnderecoRequest enderecoRequest);

    @GetMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.OK)
    List<EnderecoListResponse> getTodosEnderecoDoCliente(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente);
}
