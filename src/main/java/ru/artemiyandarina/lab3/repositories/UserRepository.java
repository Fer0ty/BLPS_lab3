package ru.artemiyandarina.lab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.artemiyandarina.lab3.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
