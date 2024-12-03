package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.ReviewRequest;
import ru.clevertec.dto.ReviewResponse;
import ru.clevertec.entity.Review;

@Mapper(uses = {CarMapper.class, ClientMapper.class}, componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "car", source = "carId")
    @Mapping(target = "client", source = "clientId")
    Review requestToEntity(ReviewRequest reviewRequest);

    @Mapping(target = "id", source = "reviewId")
    @Mapping(target = "car", source = "reviewRequest.carId")
    @Mapping(target = "client", source = "reviewRequest.clientId")
    Review requestToEntity(ReviewRequest reviewRequest, Long reviewId);

    ReviewResponse entityToResponse(Review review);
}
