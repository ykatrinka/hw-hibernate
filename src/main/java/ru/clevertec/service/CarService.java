package ru.clevertec.service;

import ru.clevertec.dto.CarCreateDto;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.dto.CarUpdateDto;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    void saveCar(CarCreateDto carRequest);

    CarResponse getCarById(Long carId);

    void updateCar(Long carId, CarUpdateDto carUpdateDto);

    void deleteCarById(Long carId);

    List<CarResponse> getAllCars();

    void assignCarToShowroom(Long carId, Long carShowroomId);

    List<CarResponse> getAllCarsJpqlFetch();

    List<CarResponse> getAllCarsWithFilter(String brand, String category,
                                           int year,
                                           BigDecimal minPrice, BigDecimal maxPrice);

    List<CarResponse> getCarsSortByPrice(String sortOrder);

    List<CarResponse> getAllCarsByPage(int page, int pageSize);
}
