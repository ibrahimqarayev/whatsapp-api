package az.whatsapp.controller;

import az.whatsapp.dto.response.UserResponse;
import az.whatsapp.model.User;
import az.whatsapp.dto.request.UserUpdateRequest;
import az.whatsapp.dto.response.ApiResponse;
import az.whatsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile(@RequestHeader("Authorization") String token) {
        UserResponse user = userService.getProfile(token);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<UserResponse>> searchUser(@PathVariable("query") String query) {
        List<UserResponse> users = userService.searchUser(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserUpdateRequest req, @RequestHeader("Authorization") String token) {
        UserResponse user = userService.getProfile(token);
        userService.updateUser(user.getId(), req);
        ApiResponse response = new ApiResponse("user updated successfully", true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.ACCEPTED);
    }
}
