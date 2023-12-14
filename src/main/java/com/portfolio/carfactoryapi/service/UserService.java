package com.portfolio.carfactoryapi.service;

import com.portfolio.carfactoryapi.exception.UserAlreadyExistsException;
import com.portfolio.carfactoryapi.model.User;
import com.portfolio.carfactoryapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(User user) throws UserAlreadyExistsException {
        if (isUserAlreadyExists(user.getUsername())) {
            throw new UserAlreadyExistsException("User already exists: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private boolean isUserAlreadyExists(String userName) {
        return userRepository.findUserByUsername(userName).isPresent();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User: " + username + " not found"));
    }
}
