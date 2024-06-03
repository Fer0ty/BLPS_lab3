package ru.artemiyandarina.lab3.schemas.petition;

import lombok.Data;
@Data
public class PetitionNotification {
    private Long id;
    private Long ownerId;
    private String approveStatus;
}
