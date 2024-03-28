package b2bapp.b2bappbackend.controller;

import b2bapp.b2bappbackend.DTO.ReviewDTO;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;
import b2bapp.b2bappbackend.exception.review.ReviewNotFoundByIdException;
import b2bapp.b2bappbackend.exception.user.UserNotFoundByIdException;
import b2bapp.b2bappbackend.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/new/{companyId}")
    public ResponseEntity createReview(@RequestBody ReviewDTO reviewDTO, @RequestParam Long userId, @PathVariable Long companyId){
        try {
            logger.info(String.format("User (userId : %d) created a review for company (companyId : %d)",userId,companyId));
            return ResponseEntity.ok().body(reviewService.createReview(reviewDTO, userId, companyId));
        } catch (UserNotFoundByIdException e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllReviews() {
        try {
            logger.info("Got all reviews");
            return ResponseEntity.ok().body(reviewService.getAllReviews());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{reviewId}/{companyId}")
    public ResponseEntity deleteReview(@PathVariable Long reviewId, @PathVariable Long companyId) {
        try {
            reviewService.deleteReview(reviewId, companyId);
            logger.info(String.format("Got review (reviewId : %d) for company (companyId : %d)"), reviewId, companyId);
            return ResponseEntity.ok().body(String.format("Отзыв с ID %d удален", reviewId));
        } catch (CompanyNotFoundByIdException e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ReviewNotFoundByIdException e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity getReviewById(@PathVariable Long reviewId) {
        try {
            logger.info(String.format("Got review (reviewId : %d)"), reviewId);
           return ResponseEntity.ok().body(reviewService.getReviewById(reviewId));
        } catch (ReviewNotFoundByIdException e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
