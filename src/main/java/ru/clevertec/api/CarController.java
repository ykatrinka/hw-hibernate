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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CarCreateDto;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.dto.CarUpdateDto;
import ru.clevertec.service.CarService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
@Validated
@Slf4j
public class CarController {

    private final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCar(@RequestBody @Valid CarCreateDto carCreateDto) {
        carService.saveCar(carCreateDto);
    }


    @GetMapping(value = "/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getCar(@PathVariable("carId") @Valid @NotNull Long carId) {
        return carService.getCarById(carId);
    }

    @PutMapping(value = "/{carId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCar(
            @PathVariable("carId") @Valid @NotNull Long carId,
            @RequestBody @Valid CarUpdateDto carUpdateDto) {
        carService.updateCar(carId, carUpdateDto);
    }

    @DeleteMapping(value = "/{carId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("carId") @Valid @NotNull Long carId) {
        carService.deleteCarById(carId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getCars() {
        return carService.getAllCars();
    }


    @PostMapping("/{carId}/showroom/{showroomId}")
    public void assignCarToShowroom(@PathVariable("carId") Long carId, @PathVariable("showroomId") Long showroomId) {
        carService.assignCarToShowroom(carId, showroomId);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getAllCarsWithFilter(@RequestParam(value = "brand", defaultValue = "") String brand,
                                                  @RequestParam(value = "category", defaultValue = "") String category,
                                                  @RequestParam(value = "year", defaultValue = "0") int year,
                                                  @RequestParam(value = "minPrice", defaultValue = "0") BigDecimal minPrice,
                                                  @RequestParam(value = "maxPrice", defaultValue = "0") BigDecimal maxPrice) {
        return carService.getAllCarsWithFilter(brand, category, year, minPrice, maxPrice);
    }
}
