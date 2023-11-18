package az.whatsapp.service;

import az.whatsapp.security.TokenProvider;
import az.whatsapp.dto.response.UserResponse;
import az.whatsapp.exception.DuplicateResourceException;
import az.whatsapp.exception.ResourceNotFoundException;
import az.whatsapp.model.User;
import az.whatsapp.repository.UserRepository;
import az.whatsapp.dto.request.UserUpdateRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DtoConversionService conversionService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, DtoConversionService conversionService, TokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse createUser(User user) {
        User isUser = findByEmail(user.getEmail());
        if (isUser != null) {
            throw new DuplicateResourceException("Email is used with another account: " + user.getEmail());
        }
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setProfilePicture(user.getProfilePicture());
        return conversionService.toUserResponse(save(newUser));
    }

    public UserResponse findUserById(int id) {
        return conversionService.toUserResponse(findById(id));
    }

    public UserResponse getProfile(String jwt) {
        return conversionService.toUserResponse(findProfile(jwt));
    }

    public User findProfile(String jwt) {
        String email = tokenProvider.getEmailFromToken(jwt);
        if (email == null) {
            throw new BadCredentialsException("received invalid token----");
        }
        User user = findByEmail(email);
        return user;
    }

    @Transactional
    public UserResponse updateUser(int userId, UserUpdateRequest request) {
        User user = findById(userId);
        return conversionService.toUserResponse(save(user));
    }

    public List<UserResponse> searchUser(String query) {
        return conversionService.toUserResponseList(userRepository.searchUser(query));
    }

    @Transactional
    public void deleteById(int id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    public List<UserResponse> findAll() {
        return conversionService.toUserResponseList(userRepository.findAll());
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    protected User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Transactional
    private User save(User user) {
        return userRepository.save(user);
    }

}
