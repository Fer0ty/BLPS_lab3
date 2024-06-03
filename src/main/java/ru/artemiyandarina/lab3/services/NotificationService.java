package ru.artemiyandarina.lab3.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.artemiyandarina.lab3.schemas.petition.PetitionNotification;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend("messages",message);
        log.info("Send: " + message);
    }
    public void sendPetitionNotification(PetitionNotification petitionNotification) {
        try {
            String message = objectMapper.writeValueAsString(petitionNotification);
            sendMessage(message);
        } catch (JsonProcessingException e) {
            log.error("Error serializing PetitionNotification: ", e);
        }
    }
}
