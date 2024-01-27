package b2bapp.b2bappbackend.controller;

import b2bapp.b2bappbackend.exception.user.UserNotFoundByIdException;
import b2bapp.b2bappbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{userId}/mycompanies")
    public ResponseEntity getMyCompanies(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok().body(userService.getMyCompanies(userId));
        } catch (UserNotFoundByIdException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
