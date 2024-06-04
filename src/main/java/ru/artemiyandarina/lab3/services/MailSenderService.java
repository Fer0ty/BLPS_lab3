package ru.artemiyandarina.lab3.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.artemiyandarina.lab3.exceptions.NotFoundException;
import ru.artemiyandarina.lab3.repositories.UserRepository;
import ru.artemiyandarina.lab3.schemas.petition.PetitionNotification;


@Slf4j
@Component
@AllArgsConstructor
@Profile({"devNotification","heliosNotification"})
public class MailSenderService {
    UserRepository userRepository;
    MailService mailService;

    @JmsListener(destination = "messages")
    public void processMessages(String json) {
        log.info("Recived: " + json);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PetitionNotification petitionNotification = objectMapper.readValue(json, PetitionNotification.class);
            String title = "Обновление статуса петиции";
            String message = String.format("Статус вашей петиции с id: %s обновлен\n\tНовый статус петиции: %s", petitionNotification.getId(), petitionNotification.getApproveStatus());
            mailService.sendSimpleEmail(userRepository.findById(petitionNotification.getOwnerId()).orElseThrow(() -> new NotFoundException(petitionNotification.getOwnerId(), "User")), title, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
