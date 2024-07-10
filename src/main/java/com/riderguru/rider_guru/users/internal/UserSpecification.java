package com.riderguru.rider_guru.users.internal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Date;

class UserSpecification {

    public static Specification<User> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(name) ? criteriaBuilder.equal(root.get("name"), name) : null;
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(email) ? criteriaBuilder.equal(root.get("email"), email) : null;
    }

    public static Specification<User> isEmailVerified(Boolean isEmailVerified) {
        return (root, query, criteriaBuilder) ->
                isEmailVerified != null ? criteriaBuilder.equal(root.get("isEmailVerified"), isEmailVerified) : null;
    }

    public static Specification<User> hasMobileNumber(String mobileNumber) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(mobileNumber) ? criteriaBuilder.equal(root.get("mobileNumber"), mobileNumber) : null;
    }

    public static Specification<User> hasDob(Date dob) {
        return (root, query, criteriaBuilder) ->
                dob != null ? criteriaBuilder.equal(root.get("dob"), dob) : null;
    }

    public static Specification<User> hasProfileImage(String profileImage) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(profileImage) ? criteriaBuilder.equal(root.get("profileImage"), profileImage) : null;
    }

    public static Specification<User> hasSosEmergencyContact(String sosEmergencyContact) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(sosEmergencyContact) ? criteriaBuilder.equal(root.get("sosEmergencyContact"), sosEmergencyContact) : null;
    }

    public static Specification<User> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) ->
                isActive != null ? criteriaBuilder.equal(root.get("isActive"), isActive) : null;
    }

    public static Specification<User> isPremium(Boolean isPremium) {
        return (root, query, criteriaBuilder) ->
                isPremium != null ? criteriaBuilder.equal(root.get("isPremium"), isPremium) : null;
    }

}
