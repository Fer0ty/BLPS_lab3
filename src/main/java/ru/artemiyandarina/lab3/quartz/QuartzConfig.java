package ru.artemiyandarina.lab3.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Calendar;
import java.util.Date;

@Profile({"devMain","heliosMain"})
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail monthlyJobDetail() {
        return JobBuilder.newJob(MonthlyJob.class)
                .withIdentity("monthlyJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger monthlyJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(monthlyJobDetail())
                .withIdentity("monthlyTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1 * ?"))
                .build();
    }

    @Bean
    public JobDetail dailyJobDetail() {
        return JobBuilder.newJob(DailyJob.class)
                .withIdentity("dailyJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger dailyJobTrigger() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date midnight = calendar.getTime();

        return TriggerBuilder.newTrigger()
                .forJob(dailyJobDetail())
                .withIdentity("dailyTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(24)
                        .repeatForever())
                .startAt(midnight)
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobDetails(dailyJobDetail(), monthlyJobDetail());
        schedulerFactoryBean.setTriggers(dailyJobTrigger(), monthlyJobTrigger());
        return schedulerFactoryBean;
    }
}
