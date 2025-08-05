package com.riderguru.rider_guru.users.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "name")
        private String name;
        @Column(name = "email", unique = true)
        private String email;
        @Column(name = "is_email_verified")
        private Boolean isEmailVerified;
        @Column(name = "mobile_number", unique = true)
        private String mobileNumber;
        @Column(name = "dob")
        private Date dob;
        @Column(name = "profile_image")
        private String profileImage;
        @Column(name = "background_image")
        private String backgroundImage;
        @Column(name = "profile_write_up")
        private String profileWriteUp;
        @Column(name = "sos_emergency_contact")
        private String sosEmergencyContact;
        @Column(name = "is_active")
        private Boolean isActive;
        @Column(name = "is_premium")
        private Boolean isPremium;
}
