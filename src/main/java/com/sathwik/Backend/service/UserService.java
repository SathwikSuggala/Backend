package com.sathwik.Backend.service;

import com.sathwik.Backend.dto.RegisterResponseDto;
import com.sathwik.Backend.dto.UserDto;
import com.sathwik.Backend.model.User;
import com.sathwik.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public RegisterResponseDto addUser(UserDto userDto) {
        //changing dto to entity
        User user = new User();
        user.setUser(userDto);
        // Check if username already exists
        if (userRepository.findByUsername(user.getUserName()).isPresent()) {
            return new RegisterResponseDto(user.getUserName(), user.getRole(),false);
        }
        // Encrypt the password
        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return new RegisterResponseDto(user.getUserName(), user.getRole(),true);
    }

    public String getUserRole(String userName) {
        User tempUser = userRepository.findByUsername(userName).orElse(null);
        return tempUser.getRole().toString();
    }

}
