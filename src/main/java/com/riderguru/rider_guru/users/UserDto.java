package com.riderguru.rider_guru.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
        private Long id;
        @NotEmpty(message = "Name needs to be entered")
        private String name;
        @NotEmpty(message = "Email needs to be entered")
        @Email
        private String email;
        private Boolean isEmailVerified;
        @NotEmpty(message = "Mobile Number needs to be entered")
        private String mobileNumber;
        @NotNull(message = "Date of Birth needs to be entered")
        private Date dob;
        private String profileImage;
        private String backgroundImage;
        private String profileWriteUp;
        private String sosEmergencyContact;
        private Boolean isActive;
        private Boolean isPremium;
}
