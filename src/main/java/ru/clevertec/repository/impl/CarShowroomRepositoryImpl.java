package ru.clevertec.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.repository.CarShowroomRepository;

@Repository
public class CarShowroomRepositoryImpl extends CrudRepositoryImpl<CarShowroom, Long>
        implements CarShowroomRepository {
    @Autowired
    public CarShowroomRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, CarShowroom.class);
    }
}
