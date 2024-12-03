package ru.clevertec.repository;

import ru.clevertec.entity.Car;
import ru.clevertec.enums.SortOrder;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    void assignCarToShowroom(Long carId, Long carShowroomId);

    List<Car> getCarsByFilters(String brand, String category, int year, Double minPrice, Double maxPrice);

    List<Car> getCarsSortByPrice(SortOrder sortOrder);

    List<Car> getAllCarsByPage(int page, int pageSize);

    List<Car> getAllCarsEntityGraph();

    List<Car> getAllCarsJpqlFetch();

    List<Car> getAllCarsCriteriaFetch();
}
