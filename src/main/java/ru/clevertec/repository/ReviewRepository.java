package ru.clevertec.repository;

import ru.clevertec.entity.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> getAllReviewsFullTextSearch(String searchText);
}
