package ru.artemiyandarina.lab3.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.artemiyandarina.lab3.config.properties.MailProperties;
import ru.artemiyandarina.lab3.models.User;

@Slf4j
@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;

    private final MailProperties mailProperties;

    public void sendSimpleEmail(User user, String title, String message) {
        if (!mailProperties.getDisable()) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("");
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject(title);
            simpleMailMessage.setText(message);
            emailSender.send(simpleMailMessage);
        }
        if (mailProperties.getLog()) {
            log.info("Отпрвлен email: \n\tTo: {}\n\tSubject: {}\n\tBody: {}", user.getEmail(), title, message);
        }
    }
    public void sendSimpleEmail (String title, String message) {
        if (!mailProperties.getDisable()) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("blpslab3artemiyandarina@gmail.com");
            simpleMailMessage.setTo("artemiy.soloviev@gmail.com");
            simpleMailMessage.setSubject(title);
            simpleMailMessage.setText(message);
            emailSender.send(simpleMailMessage);
        }
        if (mailProperties.getLog()) {
            log.info("Отпрвлен email: \n\tTo: {}\n\tSubject: {}\n\tBody: {}", "testemail", title, message);
        }
    }
}
