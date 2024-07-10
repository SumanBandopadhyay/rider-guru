package com.riderguru.rider_guru.users.internal;

import com.riderguru.rider_guru.libs.GenericService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Slf4j
@Service
public class UserService implements GenericService<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        log.info("Save user : {}", user.getName());
        User savedUser = userRepository.save(user);
        log.info("Saved user id : {}", user.getId());
        return savedUser;
    }

    @Override
    public Optional<User> getById(Long id) {
        log.info("User check for id : {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        log.info("User found for id {} : {}", id, optionalUser.isPresent());
        return optionalUser;
    }

    @Override
    public void delete(Long id) {
        log.info("Delete user id : {}", id);
        userRepository.deleteById(id);
        log.info("Delete of user id : {} complete", id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> query(Map<String, String> params) {
        log.info("User query : {}", params.toString());
        if (params.isEmpty()) {
            log.info("No criteria provided");
            return Collections.emptyList();
        }
        Specification<User> spec = Specification.where(UserSpecification.hasEmail(params.get("email")))
                .and(UserSpecification.hasName(params.get("name")))
                .and(UserSpecification.hasMobileNumber(params.get("mobileNumber")));
        List<User> users = userRepository.findAll(spec);
        log.info("User found : {}", !users.isEmpty());
        return users;
    }
}
