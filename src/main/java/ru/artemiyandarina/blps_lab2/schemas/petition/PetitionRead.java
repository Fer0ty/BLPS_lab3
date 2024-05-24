package ru.artemiyandarina.blps_lab2.schemas.petition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.artemiyandarina.blps_lab2.schemas.user.UserRead;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class PetitionRead extends PetitionBase {
    @Schema(example = "1")
    private Long id;

    private UserRead owner;
    private String country;
    private Timestamp creationDate;
}
