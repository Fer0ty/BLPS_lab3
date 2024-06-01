package ru.artemiyandarina.lab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.artemiyandarina.lab3.models.Petition;

import java.util.Optional;

public interface PetitionRepository extends JpaRepository<Petition, Long> {
    Optional<Petition> findById(Long id);
}
