package ru.clevertec.service;

import ru.clevertec.dto.CarCreateDto;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.dto.CarUpdateDto;

import java.util.List;

public interface CarService {
    void saveCar(CarCreateDto carRequest);

    CarResponse getCarById(Long carId);

    void updateCar(Long carId, CarUpdateDto carUpdateDto);

    void deleteCarById(Long carId);

    List<CarResponse> getAllCars();

    void assignCarToShowroom(Long carId, Long carShowroomId);

}
