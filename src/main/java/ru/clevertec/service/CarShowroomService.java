package ru.clevertec.service;

import ru.clevertec.dto.CarShowroomCreateDto;
import ru.clevertec.dto.CarShowroomResponse;
import ru.clevertec.dto.CarShowroomUpdateDto;

import java.util.List;

public interface CarShowroomService {
    void saveCarShowroom(CarShowroomCreateDto carShowroomCreateDto);

    CarShowroomResponse getCarShowroomById(Long carShowroomId);

    void updateCarShowroom(Long carShowroomId, CarShowroomUpdateDto carShowroomUpdateDto);

    void deleteCarShowroomById(Long carShowroomId);

    List<CarShowroomResponse> getAllCarShowrooms();
}
