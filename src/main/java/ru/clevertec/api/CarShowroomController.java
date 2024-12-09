package ru.clevertec.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CarShowroomCreateDto;
import ru.clevertec.dto.CarShowroomResponse;
import ru.clevertec.dto.CarShowroomUpdateDto;
import ru.clevertec.service.CarShowroomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carshowrooms")
@Validated
@Slf4j
public class CarShowroomController {

    private final CarShowroomService carShowroomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCarShowroom(@RequestBody @Valid CarShowroomCreateDto carShowroomCreateDto) {
        carShowroomService.saveCarShowroom(carShowroomCreateDto);
    }


    @GetMapping(value = "/{carShowroomId}")
    @ResponseStatus(HttpStatus.OK)
    public CarShowroomResponse getCarShowroom(@PathVariable("carShowroomId") @Valid @NotNull Long carShowroomId) {
        return carShowroomService.getCarShowroomById(carShowroomId);
    }

    @PutMapping(value = "/{carShowroomId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCarShowroom(
            @PathVariable("carShowroomId") @Valid @NotNull Long carShowroomId,
            @RequestBody @Valid CarShowroomUpdateDto carShowroomUpdateDto) {
        carShowroomService.updateCarShowroom(carShowroomId, carShowroomUpdateDto);
    }

    @DeleteMapping(value = "/{carShowroomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarShowroom(@PathVariable("carShowroomId") @Valid @NotNull Long carShowroomId) {
        carShowroomService.deleteCarShowroomById(carShowroomId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarShowroomResponse> getCarShowrooms() {
        return carShowroomService.getAllCarShowrooms();
    }
}
