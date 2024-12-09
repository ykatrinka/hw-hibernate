package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
