package academy.wakanda.delivery.pedido.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.CREATED)
    PedidoResponse postClienteRealizaPedido(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente,
            @Valid @RequestBody PedidoRequest pedidoRequest);

    @GetMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.OK)
    List<PedidoListCliente> getTodosPedidosDoCliente(@RequestHeader(name = "Authorization", required = true) String token, @PathVariable UUID idCliente);

    @GetMapping("/{idCliente}/{idPedido}")
    @ResponseStatus(code = HttpStatus.OK)
    PedidoDetalhadoCliente getPedidoDoClientePorId(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente,
            @PathVariable UUID idPedido);

    @PatchMapping("/entrega/{idCliente}/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchEntregaPedidoDoCliente(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente,
            @PathVariable UUID idPedido);

    @PatchMapping("/retiraEntrega/{idCliente}/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchRetiraEntregaPedidoDoCliente(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente,
            @PathVariable UUID idPedido);

    @PatchMapping("/{idCliente}/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchPedidoDoClientePorId(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente,
            @PathVariable UUID idPedido,
            @RequestBody PedidoAlteracaoRequest pedidoAlteracaoRequest);

    @DeleteMapping("/{idCliente}/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletePedidoDoClientePorId(
            @RequestHeader(name = "Authorization", required = true) String token,
            @PathVariable UUID idCliente,
            @PathVariable UUID idPedido);


}
