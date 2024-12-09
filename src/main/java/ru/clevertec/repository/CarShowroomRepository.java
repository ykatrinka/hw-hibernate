package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.CarShowroom;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, Long> {
}
