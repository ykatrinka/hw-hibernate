package ru.clevertec.service;

import ru.clevertec.dto.CarShowroomRequest;
import ru.clevertec.dto.CarShowroomResponse;

import java.util.List;

public interface CarShowroomService {
    void saveCarShowroom(CarShowroomRequest carShowroomRequest);

    CarShowroomResponse getCarShowroomById(Long carShowroomId);

    void updateCarShowroom(CarShowroomRequest carShowroomRequest, Long carShowroomId);

    void deleteCarShowroomById(Long carShowroomId);

    List<CarShowroomResponse> getAllCarShowrooms();
}
