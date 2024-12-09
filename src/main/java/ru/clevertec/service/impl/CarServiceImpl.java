package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CarCreateDto;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.dto.CarUpdateDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.exception.CarNotFoundException;
import ru.clevertec.exception.CarShowroomNotFoundException;
import ru.clevertec.logging.Logging;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.service.CarService;
import ru.clevertec.specification.CarSpecification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Logging
@Service
@RequiredArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarShowroomRepository carShowroomRepository;
    private final CarMapper carMapper;

    @Override
    public void saveCar(CarCreateDto carCreateDto) {
        Car car = carMapper.createToEntity(carCreateDto);
        carRepository.save(car);
    }

    @Transactional(readOnly = true)
    @Override
    public CarResponse getCarById(Long carId) {
        return carRepository.findById(carId)
                .map(carMapper::entityToResponse)
                .orElseThrow(() -> CarNotFoundException.byCarId(carId));
    }

    @Override
    public void updateCar(Long carId, CarUpdateDto carUpdateDto) {
        checkCarId(carId);

        Optional.ofNullable(carUpdateDto)
                .map(categoryDto -> carMapper.updateDtoToEntity(carId, categoryDto))
                .ifPresent(carRepository::save);
    }

    @Override
    public void deleteCarById(Long carId) {
        Optional.ofNullable(carId)
                .map(carRepository::findById)
                .ifPresent(id -> carRepository.deleteById(carId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarResponse> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    @Override
    public void assignCarToShowroom(Long carId, Long carShowroomId) {
        CarShowroom carShowroom = carShowroomRepository.findById(carShowroomId)
                .orElseThrow(() -> CarShowroomNotFoundException.byCarShowroomId(carShowroomId));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> CarNotFoundException.byCarId(carId));

        if (car != null) {
            car.setCarShowroom(carShowroom);
            carRepository.save(car);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarResponse> getAllCarsJpqlFetch() {
        return carRepository.getAllCarsJpqlFetch()
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarResponse> getAllCarsWithFilter(String brand, String category,
                                                  int year,
                                                  BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Car> carSpecification = Specification
                .where(CarSpecification.equalsBrand(brand))
                .and(CarSpecification.equalsCategory(category))
                .and(CarSpecification.equalsYear(year))
                .and(CarSpecification.betweenPrice(minPrice, maxPrice));


        return carRepository.findAll(carSpecification)
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarResponse> getCarsSortByPrice(String sortOrder) {
        return carRepository.findAll(Sort.by(sortOrder, "price"))
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarResponse> getAllCarsByPage(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return carRepository.findAll(pageRequest)
                .stream()
                .map(carMapper::entityToResponse)
                .toList();
    }

    private void checkCarId(Long carId) {
        Optional.ofNullable(carId)
                .map(carRepository::getById)
                .orElseThrow(() -> CarNotFoundException.byCarId(carId));
    }

}
