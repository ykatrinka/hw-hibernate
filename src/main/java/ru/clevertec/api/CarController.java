package ru.clevertec.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CarRequest;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.service.CarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("car")
public class CarController {

    private final CarService carService;

    @PostMapping
    public void addCar(@RequestBody CarRequest carRequest) {
        carService.saveCar(carRequest);
    }

    @GetMapping
    public List<CarResponse> getAllCarsWithFilter(@RequestParam("brand") String brand,
                                                  @RequestParam("category") String category,
                                                  @RequestParam("year") int year,
                                                  @RequestParam("minPrice") Double minPrice,
                                                  @RequestParam("maxPrice") Double maxPrice) {
        return carService.getAllCarsWithFilter(brand, category, year, minPrice, maxPrice);
    }

    @PostMapping("/{carId}/showroom/{showroomId}")
    public void assignCarToShowroom(@PathVariable Long carId, @PathVariable Long showroomId) {
        carService.assignCarToShowroom(carId, showroomId);
    }
}
