package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.CarShowroomCreateDto;
import ru.clevertec.dto.CarShowroomResponse;
import ru.clevertec.dto.CarShowroomUpdateDto;
import ru.clevertec.entity.CarShowroom;

@Mapper(componentModel = "spring")
public interface CarShowroomMapper {

    @Mapping(target = "id", ignore = true)
    CarShowroom createDtoToEntity(CarShowroomCreateDto carShowroomRequest);

    @Mapping(target = "id", source = "carShowroomId")
    CarShowroom updateDtoToEntity(CarShowroomUpdateDto carShowroomUpdateDto, Long carShowroomId);

    CarShowroomResponse entityToResponse(CarShowroom carShowroom);

}
