package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.CarShowroomRequest;
import ru.clevertec.dto.CarShowroomResponse;
import ru.clevertec.entity.CarShowroom;

@Mapper
public interface CarShowroomMapper {

    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "id", ignore = true)
    CarShowroom requestToEntity(CarShowroomRequest carShowroomRequest);

    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "id", source = "carShowroomId")
    CarShowroom requestToEntity(CarShowroomRequest carShowroomRequest, Long carShowroomId);

    CarShowroomResponse entityToResponse(CarShowroom carShowroom);

}
