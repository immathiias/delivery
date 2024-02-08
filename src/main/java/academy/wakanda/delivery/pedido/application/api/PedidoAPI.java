package academy.wakanda.delivery.pedido.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/pedido")
public interface PedidoAPI {
    @PostMapping("/criaEndereco/{idCliente}")
    @ResponseStatus(code = HttpStatus.CREATED)
    PedidoResponse postClienteRealizaPedidoCriandoEndereco(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente,
            @Valid @RequestBody PedidoRequestCriandoEndereco pedidoRequest);

    @GetMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.OK)
    List<PedidoListCliente> getTodosPedidosDoCliente(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable UUID idCliente);

}
