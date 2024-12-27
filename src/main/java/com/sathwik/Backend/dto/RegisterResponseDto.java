package com.sathwik.Backend.dto;

import com.sathwik.Backend.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {

    private String userName;
    private Role role;
    private boolean status;
}
