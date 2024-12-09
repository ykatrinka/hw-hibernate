package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.ReviewCreateDto;
import ru.clevertec.dto.ReviewResponse;
import ru.clevertec.dto.ReviewUpdateDto;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.exception.CarAnotherClientException;
import ru.clevertec.exception.ClientNotFoundException;
import ru.clevertec.exception.ReviewNotFoundException;
import ru.clevertec.mapper.ReviewMapper;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.repository.ReviewRepository;
import ru.clevertec.service.ReviewService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ClientRepository clientRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public void saveReview(ReviewCreateDto reviewCreateDto) {
        checkClientCars(reviewCreateDto.clientId(), reviewCreateDto.carId());

        Review review = reviewMapper.createDtoToEntity(reviewCreateDto);
        reviewRepository.save(review);
    }

    @Override
    public ReviewResponse getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .map(reviewMapper::entityToResponse)
                .orElseThrow(() -> ReviewNotFoundException.byReviewId(reviewId));
    }

    @Override
    public void updateReview(Long reviewId, ReviewUpdateDto reviewUpdateDto) {
        checkReviewId(reviewId);
        checkClientCars(reviewUpdateDto.clientId(), reviewUpdateDto.carId());

        Review review = reviewMapper.updateDtoToEntity(reviewUpdateDto, reviewId);
        reviewRepository.save(review);
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        Optional.ofNullable(reviewId)
                .map(reviewRepository::findById)
                .ifPresent(id -> reviewRepository.deleteById(reviewId));
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::entityToResponse)
                .toList();
    }

    private void checkReviewId(Long reviewId) {
        Optional.ofNullable(reviewId)
                .map(reviewRepository::getById)
                .orElseThrow(() -> ReviewNotFoundException.byReviewId(reviewId));
    }

    private void checkClientCars(Long clientId, Long carId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> ClientNotFoundException.byClientId(clientId));

        if (client.getCars() == null) {
            throw CarAnotherClientException.byCarIdAndClientId(
                    carId,
                    client.getId()
            );
        }

        boolean isCarNotExists = client.getCars()
                .stream()
                .noneMatch(car -> car.getId().equals(carId));

        if (isCarNotExists) {
            throw CarAnotherClientException.byCarIdAndClientId(
                    carId,
                    client.getId()
            );
        }
    }

}
