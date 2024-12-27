package com.sathwik.Backend.controller;



import com.sathwik.Backend.dto.AuthRequestDto;
import com.sathwik.Backend.dto.LoginResponseDto;
import com.sathwik.Backend.dto.RegisterResponseDto;
import com.sathwik.Backend.dto.UserDto;
import com.sathwik.Backend.jwt.service.JwtService;
import com.sathwik.Backend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@RequestBody UserDto userDto){

        try{
            RegisterResponseDto dto = userService.addUser(userDto);
            if(dto.isStatus()) {
                return ResponseEntity.ok(dto);
            }else {
                return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
            }
        }
        catch (IllegalArgumentException iae){
            return new ResponseEntity<>(iae.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse response){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUserName(), authRequestDto.getPassword()));

        if(authentication.isAuthenticated()) {
            String role = userService.getUserRole(authRequestDto.getUserName());
            String jwt = jwtService.generateToken(authRequestDto.getUserName(), role);
            jwtService.addTokenToResponse(response, jwt, role);
            return new ResponseEntity<>(new LoginResponseDto(jwt, role), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    }

