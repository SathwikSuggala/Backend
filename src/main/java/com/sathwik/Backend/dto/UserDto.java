package com.sathwik.Backend.dto;



import com.sathwik.Backend.model.User;
import com.sathwik.Backend.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;
    private String password;
    private String email;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private Gender gender;  // Enum

    public void setUserDto(User user){
        setUserName(user.getUserName());
        setPassword(null);
        setEmail(user.getEmail());
        setMobileNumber(user.getMobileNumber());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setDateOfBirth(user.getDateOfBirth());
        setGender(user.getGender());
    }
}
