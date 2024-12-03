package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.ReviewRequest;
import ru.clevertec.dto.ReviewResponse;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.exception.CarAnotherClientException;
import ru.clevertec.exception.CarNotFoundException;
import ru.clevertec.exception.ClientNotFoundException;
import ru.clevertec.exception.ReviewBadRequestException;
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
    public void saveReview(ReviewRequest reviewRequest) {
        checkReviewRequest(reviewRequest);
        checkCarId(reviewRequest.getCarId());
        Client client = checkClient(reviewRequest.getClientId());
        checkClientCars(client, reviewRequest.getCarId());

        Review review = reviewMapper.requestToEntity(reviewRequest);
        reviewRepository.save(review);
    }

    @Override
    public ReviewResponse getReviewById(Long reviewId) {
        checkReviewId(reviewId);

        Review review = reviewRepository.getById(reviewId);
        return reviewMapper.entityToResponse(review);
    }

    @Override
    public void updateReview(ReviewRequest reviewRequest, Long reviewId) {
        checkReviewId(reviewId);
        checkReviewRequest(reviewRequest);
        checkCarId(reviewRequest.getCarId());
        Client client = checkClient(reviewRequest.getClientId());
        checkClientCars(client, reviewRequest.getCarId());

        Review review = reviewMapper.requestToEntity(reviewRequest, reviewId);
        reviewRepository.update(review);
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        checkReviewId(reviewId);
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.getAll()
                .stream()
                .map(reviewMapper::entityToResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> getAllReviewsFullTextSearch(String searchText) {
        return reviewRepository.getAllReviewsFullTextSearch(searchText)
                .stream()
                .map(reviewMapper::entityToResponse)
                .toList();
    }

    private void checkReviewRequest(ReviewRequest reviewRequest) {
        if (reviewRequest == null) {
            throw ReviewBadRequestException.byReviewRequest();
        }
    }

    private void checkReviewId(Long reviewId) {
        Optional.ofNullable(reviewId)
                .map(reviewRepository::getById)
                .orElseThrow(() -> ReviewNotFoundException.byReviewId(reviewId));
    }

    private void checkCarId(Long carId) {
        if (carId == null) {
            throw CarNotFoundException.byCarId(carId);
        }
    }

    private Client checkClient(Long clientId) {
        return Optional.ofNullable(clientId)
                .map(clientRepository::getById)
                .orElseThrow(() -> ClientNotFoundException.byClientId(clientId));
    }

    private void checkClientCars(Client client, Long carId) {
        if (client.getCars() == null) {
            throw CarAnotherClientException.byCarIdAndClientId(
                    carId,
                    client.getId()
            );
        }

        if (isCarNotExists(client.getCars(), carId)) {
            throw CarAnotherClientException.byCarIdAndClientId(
                    carId,
                    client.getId()
            );
        }
    }

    private static boolean isCarNotExists(List<Car> cars, Long carId) {
        return cars.stream().noneMatch(car -> car.getId().equals(carId));
    }

}
