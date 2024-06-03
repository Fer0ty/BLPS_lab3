package ru.artemiyandarina.lab3.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Data
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "application.mail")
public class MailProperties {
    // Выводит в консоль текст сообщения
    private Boolean log = false;
    // Отключает отправку сообщения
    private Boolean disable = false;
}
