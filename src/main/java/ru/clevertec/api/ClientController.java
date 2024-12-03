package ru.clevertec.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.ClientRequest;
import ru.clevertec.service.ClientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public void addClient(@RequestBody ClientRequest clientRequest) {
        clientService.saveClient(clientRequest);
    }


    @PostMapping("/{clientId}/car/{carId}")
    public void buyCar(@PathVariable Long clientId, @PathVariable Long carId) {
        clientService.buyCar(clientId, carId);
    }
}
