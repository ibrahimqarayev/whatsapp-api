package az.whatsapp.controller;

import az.whatsapp.security.TokenProvider;
import az.whatsapp.model.User;
import az.whatsapp.dto.request.LoginRequest;
import az.whatsapp.dto.response.AuthResponse;
import az.whatsapp.security.CustomUserDetailsService;
import az.whatsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;


    public AuthController(PasswordEncoder passwordEncoder, TokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) {
        String email = user.getEmail();
        String fullName = user.getFullName();
        String password = user.getPassword();

        userService.createUser(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(jwt, true);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        String email = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(jwt, true);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);

    }

    public Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
