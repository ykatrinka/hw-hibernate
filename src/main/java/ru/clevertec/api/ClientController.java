package ru.clevertec.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.ClientCreateDto;
import ru.clevertec.dto.ClientResponse;
import ru.clevertec.dto.ClientUpdateDto;
import ru.clevertec.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        clientService.saveClient(clientCreateDto);
    }

    @GetMapping(value = "/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse getClient(@PathVariable("clientId") @Valid @NotNull Long clientId) {
        return clientService.getClientById(clientId);
    }

    @PutMapping(value = "/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateClient(
            @PathVariable("clientId") @Valid @NotNull Long clientId,
            @RequestBody @Valid ClientUpdateDto clientUpdateDto) {
        clientService.updateClient(clientId, clientUpdateDto);
    }

    @DeleteMapping(value = "/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("clientId") @Valid @NotNull Long clientId) {
        clientService.deleteClientById(clientId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getClients() {
        return clientService.getAllClients();
    }


    @PostMapping("/{clientId}/car/{carId}")
    public void buyCar(@PathVariable("clientId") Long clientId,
                       @PathVariable("carId") Long carId) {
        clientService.buyCar(clientId, carId);
    }
}
