package com.riderguru.rider_guru.users.internal;

import com.riderguru.rider_guru.libs.GenericService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class for managing User entities.
 * Provides CRUD operations and complex queries.
 * Logs each operation for better observability.
 */
@Transactional
@Slf4j
@Service
class UserService implements GenericService<User> {

    private final UserRepository userRepository;

    /**
     * Constructs a UserService with the provided repository.
     *
     * @param userRepository repository used to interact with the database
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a user to the database.
     *
     * @param user the user to save
     * @return the saved user
     */
    @Override
    public User save(User user) {
        log.info("Saving user with name: {}", user.getName());
        User savedUser = userRepository.save(user);
        log.info("User saved successfully with id: {}", savedUser.getId());
        return savedUser;
    }

    /**
     * Retrieves a user by its identifier.
     *
     * @param id the user identifier
     * @return an Optional containing the user if found, empty otherwise
     */
    @Override
    public Optional<User> getById(Long id) {
        log.info("Retrieving user with id: {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        log.info("User retrieval for id {} resulted in: {}", id, optionalUser.isPresent());
        return optionalUser;
    }

    /**
     * Deletes a user by its identifier.
     *
     * @param id the user identifier
     */
    @Override
    public void delete(Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
        log.info("Deletion complete for user id: {}", id);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return list of all users
     */
    @Override
    public List<User> getAll() {
        log.info("Retrieving all users");
        List<User> users = userRepository.findAll();
        log.info("Retrieved {} users", users.size());
        return users;
    }

    /**
     * Queries users based on provided parameters.
     * Supported parameters include email, name, mobileNumber, isEmailVerified, dob, profileImage,
     * sosEmergencyContact, isActive and isPremium.
     *
     * @param params map of query parameters
     * @return list of users matching the criteria
     */
    @Override
    public List<User> query(Map<String, String> params) {
        log.info("Querying users with parameters: {}", params);
        if (params.isEmpty()) {
            log.info("No criteria provided for user query");
            return Collections.emptyList();
        }

        Specification<User> spec = null;
        try {
            spec = Specification.where(UserSpecification.hasEmail(params.get("email")))
                    .and(UserSpecification.hasName(params.get("name")))
                    .and(UserSpecification.hasMobileNumber(params.get("mobileNumber")))
                    .and(UserSpecification.isEmailVerified(Boolean.parseBoolean(params.get("isEmailVerified"))))
                    .and(UserSpecification.hasDob(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("dob"))))
                    .and(UserSpecification.hasProfileImage(params.get("profileImage")))
                    .and(UserSpecification.hasSosEmergencyContact(params.get("sosEmergencyContact")))
                    .and(UserSpecification.isActive(Boolean.parseBoolean(params.get("isActive"))))
                    .and(UserSpecification.isPremium(Boolean.parseBoolean(params.get("isPremium"))));
        } catch (ParseException e) {
            log.error("Error parsing date during user query", e);
            throw new RuntimeException(e);
        }

        List<User> users = userRepository.findAll(spec);
        log.info("Found {} users for provided criteria", users.size());
        return users;
    }
}
