package ru.artemiyandarina.lab3.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.artemiyandarina.lab3.services.EnterRecordService;
import ru.artemiyandarina.lab3.services.MailService;

@Component
public class MonthlyJob implements Job {

    @Autowired
    private MailService emailService;

    @Autowired
    private EnterRecordService enterRecordService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String report = enterRecordService.getMonthlyReport();
        emailService.sendSimpleEmail("Ежемесячный отчет.", report);
    }
}
