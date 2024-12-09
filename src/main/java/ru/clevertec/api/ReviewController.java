package ru.clevertec.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.ReviewCreateDto;
import ru.clevertec.dto.ReviewResponse;
import ru.clevertec.dto.ReviewUpdateDto;
import ru.clevertec.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@RequestBody @Valid ReviewCreateDto reviewCreateDto) {
        reviewService.saveReview(reviewCreateDto);
    }


    @GetMapping(value = "/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse getReview(@PathVariable("reviewId") @Valid @NotNull Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @PutMapping(value = "/{reviewId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateReview(
            @PathVariable("reviewId") @Valid @NotNull Long reviewId,
            @RequestBody @Valid ReviewUpdateDto reviewUpdateDto) {
        reviewService.updateReview(reviewId, reviewUpdateDto);
    }

    @DeleteMapping(value = "/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable("reviewId") @Valid @NotNull Long reviewId) {
        reviewService.deleteReviewById(reviewId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviews() {
        return reviewService.getAllReviews();
    }

}
