package ru.artemiyandarina.lab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.artemiyandarina.lab3.models.EnterRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface EnterRecordRepository extends JpaRepository<EnterRecord, Long> {
    List<EnterRecord> findAllByTimestampBetween(LocalDateTime start, LocalDateTime end);
}

