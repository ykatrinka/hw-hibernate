package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.ReviewCreateDto;
import ru.clevertec.dto.ReviewResponse;
import ru.clevertec.dto.ReviewUpdateDto;
import ru.clevertec.entity.Review;

@Mapper(uses = {CarMapper.class, ClientMapper.class}, componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "car", source = "carId")
    @Mapping(target = "client", source = "clientId")
    Review createDtoToEntity(ReviewCreateDto reviewCreateDto);

    @Mapping(target = "id", source = "reviewId")
    @Mapping(target = "car", source = "reviewUpdateDto.carId")
    @Mapping(target = "client", source = "reviewUpdateDto.clientId")
    Review updateDtoToEntity(ReviewUpdateDto reviewUpdateDto, Long reviewId);

    ReviewResponse entityToResponse(Review review);
}
