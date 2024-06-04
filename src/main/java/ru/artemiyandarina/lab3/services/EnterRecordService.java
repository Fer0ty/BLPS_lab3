package ru.artemiyandarina.lab3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.artemiyandarina.lab3.models.EnterRecord;
import ru.artemiyandarina.lab3.repositories.EnterRecordRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnterRecordService {
    @Autowired
    private EnterRecordRepository enterRecordRepository;

    @Transactional
    public void saveEnterRecord(long count) {
        EnterRecord record = new EnterRecord();
        record.setTimestamp(LocalDateTime.now());
        record.setCount(count);
        enterRecordRepository.save(record);
    }

    public String getMonthlyReport() {
        LocalDateTime startOfMonth = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
        LocalDateTime endOfMonth = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);

        List<EnterRecord> records = enterRecordRepository.findAllByTimestampBetween(startOfMonth, endOfMonth);
        return records.stream()
                .map(record -> record.getTimestamp().toLocalDate() + ": " + record.getCount())
                .collect(Collectors.joining("\n"));
    }
}
