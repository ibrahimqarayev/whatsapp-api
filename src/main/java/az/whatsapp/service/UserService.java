package az.whatsapp.service;

import az.whatsapp.config.TokenProvider;
import az.whatsapp.exception.UserException;
import az.whatsapp.model.User;
import az.whatsapp.repository.UserRepository;
import az.whatsapp.request.UserUpdateRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public UserService(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public User findUserById(Integer id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new UserException("User not found");
    }

    public User findUserProfile(String jwt) {
        String email = tokenProvider.getEmailFromToken(jwt);
        if (email == null) {
            throw new BadCredentialsException("received invalid token----");
        }
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with email: " + email);
        }
        return user;
    }

    public User updateUser(Integer userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found with id" + userId));
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }
        if (request.getProfilePicture() != null) {
            user.setProfilePicture(request.getProfilePicture());
        }
        return userRepository.save(user);
    }

    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }


    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    List<User> findAll() {
        return userRepository.findAll();
    }

}
