package ru.clevertec.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.ReviewRequest;
import ru.clevertec.dto.ReviewResponse;
import ru.clevertec.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public void addReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.saveReview(reviewRequest);
    }


    @GetMapping
    public List<ReviewResponse> getReviewsSearch(@RequestParam("searchText") String searchText) {
        return reviewService.getAllReviewsFullTextSearch(searchText);
    }
    
}
