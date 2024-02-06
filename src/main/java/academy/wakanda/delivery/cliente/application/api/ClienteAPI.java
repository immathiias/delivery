package academy.wakanda.delivery.cliente.application.api;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public/v1/cliente")
public interface ClienteAPI {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    ClienteResponse postCliente(@Valid @RequestBody ClienteRequest clienteRequest);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<ClienteListResponse> getTodosClientes();

    @GetMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.OK)
    ClienteDetalhadoResponse getClientePorId(@PathVariable UUID idCliente);

    @PatchMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchClientePorId(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable UUID idCliente, @RequestBody ClienteAlteracaoRequest clienteAlteracaoRequest);
    @DeleteMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteClientePorId(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable UUID idCliente);
}
