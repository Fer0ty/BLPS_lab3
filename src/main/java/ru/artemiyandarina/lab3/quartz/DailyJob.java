package ru.artemiyandarina.lab3.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.artemiyandarina.lab3.controllers.RequestCounterFilter;
import ru.artemiyandarina.lab3.services.EnterRecordService;

@Component
public class DailyJob implements Job {

    @Autowired
    private RequestCounterFilter requestCounterFilter;

    @Autowired
    private EnterRecordService enterRecordService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long requestCount = requestCounterFilter.getRequestCount();
        enterRecordService.saveEnterRecord(requestCount);
    }
}

