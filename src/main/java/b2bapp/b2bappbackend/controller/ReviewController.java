package b2bapp.b2bappbackend.controller;

import b2bapp.b2bappbackend.DTO.ReviewDTO;
import b2bapp.b2bappbackend.entity.ReviewEntity;
import b2bapp.b2bappbackend.exception.user.UserNotFoundByIdException;
import b2bapp.b2bappbackend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/new/{companyId}")
    public ResponseEntity createReview(@RequestBody ReviewDTO reviewDTO, @RequestParam Long userId, @PathVariable Long companyId){
        try {
            return ResponseEntity.ok().body(reviewService.createReview(reviewDTO, userId, companyId));
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllReviews() {
        try {
            return ResponseEntity.ok().body(reviewService.getAllReviews());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
