package ru.artemiyandarina.blps_lab2.repositories;

import ru.artemiyandarina.blps_lab2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
