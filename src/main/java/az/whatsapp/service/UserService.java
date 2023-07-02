package az.whatsapp.service;

import az.whatsapp.exception.UserException;
import az.whatsapp.model.User;
import az.whatsapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Integer id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new UserException("User not found");
    }


}
