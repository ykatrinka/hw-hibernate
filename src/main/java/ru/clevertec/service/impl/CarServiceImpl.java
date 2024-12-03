package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.dto.CarRequest;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.entity.Car;
import ru.clevertec.enums.SortOrder;
import ru.clevertec.exception.CarBadRequestException;
import ru.clevertec.exception.CarNotFoundException;
import ru.clevertec.exception.CarShowroomNotFoundException;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.service.CarService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarShowroomRepository carShowroomRepository;
    private final CarMapper carMapper;

    @Override
    public void saveCar(CarRequest carRequest) {
        checkCarRequest(carRequest);

        Car car = carMapper.requestToEntity(carRequest);
        carRepository.save(car);
    }

    @Override
    public CarResponse getCarById(Long carId) {
        checkCarId(carId);

        Car car = carRepository.getById(carId);
        return carMapper.entityToResponse(car);
    }

    @Override
    public void updateCar(CarRequest carRequest, Long carId) {
        checkCarRequest(carRequest);
        checkCarId(carId);

        Car car = carMapper.requestToEntity(carRequest, carId);
        carRepository.update(car);
    }

    @Override
    public void deleteCarById(Long carId) {
        checkCarId(carId);

        carRepository.deleteById(carId);
    }

    @Override
    public List<CarResponse> getAllCars() {
        return carRepository.getAll()
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    @Override
    public void assignCarToShowroom(Long carId, Long carShowroomId) {
        checkCarId(carId);
        checkCarShowroomId(carShowroomId);

        carRepository.assignCarToShowroom(carId, carShowroomId);
    }


    @Override
    public List<CarResponse> getAllCarsWithFilter(String brand, String category, int year, Double minPrice, Double maxPrice) {
        return carRepository.getCarsByFilters(brand, category, year, minPrice, maxPrice)
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getCarsSortByPrice(SortOrder sortOrder) {
        return carRepository.getCarsSortByPrice(sortOrder)
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getAllCarsByPage(int page, int pageSize) {
        return carRepository.getAllCarsByPage(page, pageSize)
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    private void checkCarRequest(CarRequest carRequest) {
        if (carRequest == null) {
            throw CarBadRequestException.byCarRequest();
        }
    }

    private void checkCarId(Long carId) {
        Optional.ofNullable(carId)
                .map(carRepository::getById)
                .orElseThrow(() -> CarNotFoundException.byCarId(carId));
    }

    private void checkCarShowroomId(Long carShowroomId) {
        Optional.ofNullable(carShowroomId)
                .map(carShowroomRepository::getById)
                .orElseThrow(() -> CarShowroomNotFoundException.byCarShowroomId(carShowroomId));
    }

}
