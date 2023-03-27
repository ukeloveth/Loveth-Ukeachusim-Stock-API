package com.assessment.stockapi.repositories;

import com.assessment.stockapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
