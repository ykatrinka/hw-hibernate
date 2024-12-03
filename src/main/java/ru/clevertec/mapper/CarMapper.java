package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.CarRequest;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Category;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "carShowroom", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "category", source = "categoryId")
    Car requestToEntity(CarRequest carRequest);

    @Mapping(target = "carShowroom", ignore = true)
    @Mapping(target = "id", source = "carId")
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "category", source = "carRequest.categoryId")
    Car requestToEntity(CarRequest carRequest, Long carId);

    CarResponse entityToResponse(Car car);

    default Car fromId(Long carId) {
        return carId != null ? Car.builder().id(carId).build() : null;
    }

    default Category fromCategoryId(Long categoryId) {
        return categoryId != null ? Category.builder().id(categoryId).build() : null;
    }
}
