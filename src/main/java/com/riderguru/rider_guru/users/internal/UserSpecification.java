package com.riderguru.rider_guru.users.internal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class UserSpecification {

    public static Specification<User> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(name) ? criteriaBuilder.equal(root.get("name"), name) : null;
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(email) ? criteriaBuilder.equal(root.get("email"), email) : null;
    }

    public static Specification<User> hasMobileNumber(String mobileNumber) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(mobileNumber) ? criteriaBuilder.equal(root.get("mobileNumber"), mobileNumber) : null;
    }

}
