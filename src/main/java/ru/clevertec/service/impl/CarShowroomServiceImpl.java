package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CarShowroomCreateDto;
import ru.clevertec.dto.CarShowroomResponse;
import ru.clevertec.dto.CarShowroomUpdateDto;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.exception.CarShowroomNotFoundException;
import ru.clevertec.logging.Logging;
import ru.clevertec.mapper.CarShowroomMapper;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.service.CarShowroomService;

import java.util.List;
import java.util.Optional;

@Logging
@Service
@RequiredArgsConstructor
@Transactional
public class CarShowroomServiceImpl implements CarShowroomService {

    private final CarShowroomRepository carShowroomRepository;
    private final CarShowroomMapper carShowroomMapper;

    @Override
    public void saveCarShowroom(CarShowroomCreateDto carShowroomCreateDto) {
        CarShowroom carShowroom = carShowroomMapper.createDtoToEntity(carShowroomCreateDto);
        carShowroomRepository.save(carShowroom);
    }

    @Transactional(readOnly = true)
    @Override
    public CarShowroomResponse getCarShowroomById(Long carShowroomId) {
        return carShowroomRepository.findById(carShowroomId)
                .map(carShowroomMapper::entityToResponse)
                .orElseThrow(() -> CarShowroomNotFoundException.byCarShowroomId(carShowroomId));
    }

    @Override
    public void updateCarShowroom(Long carShowroomId, CarShowroomUpdateDto carShowroomUpdateDto) {
        checkCarShowroomId(carShowroomId);

        Optional.ofNullable(carShowroomUpdateDto)
                .map(showroomPutDto -> carShowroomMapper.updateDtoToEntity(showroomPutDto, carShowroomId))
                .ifPresent(carShowroomRepository::save);
    }

    @Override
    public void deleteCarShowroomById(Long carShowroomId) {
        Optional.ofNullable(carShowroomId)
                .map(carShowroomRepository::findById)
                .ifPresent(id -> carShowroomRepository.deleteById(carShowroomId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarShowroomResponse> getAllCarShowrooms() {
        return carShowroomRepository.findAll()
                .stream()
                .map(carShowroomMapper::entityToResponse)
                .toList();
    }

    private void checkCarShowroomId(Long carShowroomId) {
        Optional.ofNullable(carShowroomId)
                .map(carShowroomRepository::findById)
                .orElseThrow(() -> CarShowroomNotFoundException.byCarShowroomId(carShowroomId));
    }

}
