package ru.clevertec.service;

import ru.clevertec.dto.CarRequest;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.enums.SortOrder;

import java.util.List;

public interface CarService {
    void saveCar(CarRequest carRequest);

    CarResponse getCarById(Long carId);

    void updateCar(CarRequest carRequest, Long carId);

    void deleteCarById(Long carId);

    List<CarResponse> getAllCarsEntityGraph();

    List<CarResponse> getAllCarsJpqlFetch();

    List<CarResponse> getAllCarsCriteriaFetch();

    void assignCarToShowroom(Long carId, Long carShowroomId);

    List<CarResponse> getAllCars();

    List<CarResponse> getAllCarsWithFilter(String brand, String category, int year, Double minPrice, Double maxPrice);

    List<CarResponse> getCarsSortByPrice(SortOrder sortOrder);

    List<CarResponse> getAllCarsByPage(int page, int pageSize);
}
