package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.CarCreateDto;
import ru.clevertec.dto.CarResponse;
import ru.clevertec.dto.CarUpdateDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Category;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "carShowroom", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId")
    Car createToEntity(CarCreateDto carCreateDto);

    @Mapping(target = "carShowroom", ignore = true)
    @Mapping(target = "id", source = "carId")
    @Mapping(target = "category", source = "carUpdateDto.categoryId")
    Car updateDtoToEntity(Long carId, CarUpdateDto carUpdateDto);

    CarResponse entityToResponse(Car car);

    default Car fromId(Long carId) {
        return carId != null ? Car.builder().id(carId).build() : null;
    }

    default Category fromCategoryId(Long categoryId) {
        return categoryId != null ? Category.builder().id(categoryId).build() : null;
    }
}
