package academy.wakanda.delivery.cliente.application.api;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ClienteController implements ClienteAPI {
    @Override
    public ClienteResponse postCliente(ClienteRequest clienteRequest) {
        log.info("[inicia] ClienteController - postCliente");
        log.info("[finaliza] ClienteController - postCliente");
        return null;
    }
}
