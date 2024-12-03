package ru.clevertec.repository.impl;

import lombok.experimental.SuperBuilder;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.repository.CarShowroomRepository;

@SuperBuilder
public class CarShowroomRepositoryImpl extends CrudRepositoryImpl<CarShowroom, Long>
        implements CarShowroomRepository {
}
