package az.whatsapp.controller;

import az.whatsapp.model.User;
import az.whatsapp.request.UserUpdateRequest;
import az.whatsapp.response.ApiResponse;
import az.whatsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) {
        User user = userService.findUserProfile(token);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String q) {
        List<User> users = userService.searchUser(q);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestBody UserUpdateRequest req, @RequestHeader("Authorization") String token) {
        User user = userService.findUserProfile(token);
        userService.updateUser(user.getId(), req);
        ApiResponse response = new ApiResponse("user updated successfully", true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.ACCEPTED);
    }

}
