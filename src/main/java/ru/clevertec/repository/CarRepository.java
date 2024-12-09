package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
