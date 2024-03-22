package b2bapp.b2bappbackend.controller;

import b2bapp.b2bappbackend.exception.review.ReviewNotFoundByIdException;
import b2bapp.b2bappbackend.exception.user.UserIsNotAdminException;
import b2bapp.b2bappbackend.exception.user.UserNotFoundByIdException;
import b2bapp.b2bappbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{userId}/mycompanies")
    public ResponseEntity getMyCompanies(@PathVariable Long userId) {
        try {
            logger.info(String.format("Got company list for user, User ID : %d", userId));
            return ResponseEntity.ok().body(userService.getMyCompanies(userId));
        } catch (UserNotFoundByIdException e){
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/moderate/{reviewId}")
    public ResponseEntity moderateReview(@PathVariable Long reviewId, @RequestParam Long userId) {
        try {
            userService.moderateReview(userId,reviewId);
            return ResponseEntity.ok().body(String.format("Отзыв с ID %d прошел модерацию.", reviewId));
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserIsNotAdminException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ReviewNotFoundByIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
