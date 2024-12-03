package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.dto.CarShowroomRequest;
import ru.clevertec.dto.CarShowroomResponse;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.exception.CarShowroomBadRequestException;
import ru.clevertec.exception.CarShowroomNotFoundException;
import ru.clevertec.mapper.CarShowroomMapper;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.service.CarShowroomService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CarShowroomServiceImpl implements CarShowroomService {

    private final CarShowroomRepository carShowroomRepository;
    private final CarShowroomMapper carShowroomMapper;

    @Override
    public void saveCarShowroom(CarShowroomRequest carShowroomRequest) {
        checkCarShowroomRequest(carShowroomRequest);

        CarShowroom carShowroom = carShowroomMapper.requestToEntity(carShowroomRequest);
        carShowroomRepository.save(carShowroom);
    }

    @Override
    public CarShowroomResponse getCarShowroomById(Long carShowroomId) {
        checkCarShowroomId(carShowroomId);

        CarShowroom carShowroom = carShowroomRepository.getById(carShowroomId);
        return carShowroomMapper.entityToResponse(carShowroom);
    }

    @Override
    public void updateCarShowroom(CarShowroomRequest carShowroomRequest, Long carShowroomId) {
        checkCarShowroomRequest(carShowroomRequest);
        checkCarShowroomId(carShowroomId);

        CarShowroom carShowroom = carShowroomMapper.requestToEntity(carShowroomRequest, carShowroomId);
        carShowroomRepository.update(carShowroom);
    }

    @Override
    public void deleteCarShowroomById(Long carShowroomId) {
        checkCarShowroomId(carShowroomId);

        carShowroomRepository.deleteById(carShowroomId);
    }

    @Override
    public List<CarShowroomResponse> getAllCarShowrooms() {
        return carShowroomRepository.getAll()
                .stream()
                .map(carShowroomMapper::entityToResponse)
                .toList();
    }

    private void checkCarShowroomRequest(CarShowroomRequest carShowroomRequest) {
        if (carShowroomRequest == null) {
            throw CarShowroomBadRequestException.byCarShowroomRequest();
        }
    }

    private void checkCarShowroomId(Long carShowroomId) {
        Optional.ofNullable(carShowroomId)
                .map(carShowroomRepository::getById)
                .orElseThrow(() -> CarShowroomNotFoundException.byCarShowroomId(carShowroomId));
    }

}
