package com.sathwik.Backend.model;

import com.sathwik.Backend.util.Gender;
import com.sathwik.Backend.util.Role;
import com.sathwik.Backend.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String email;
    private String mobileNumber;

    @Column(nullable = false)
    private Role role;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

     public void setUser(UserDto dto){
         setUserName(dto.getUserName());
         setPassword(dto.getPassword());
         setEmail(dto.getEmail());
         setMobileNumber(dto.getMobileNumber());
         setRole(Role.CUSTOMER);
         setFirstName(dto.getFirstName());
         setLastName(dto.getLastName());
         setDateOfBirth(dto.getDateOfBirth());
         setGender(dto.getGender());
     }

}
