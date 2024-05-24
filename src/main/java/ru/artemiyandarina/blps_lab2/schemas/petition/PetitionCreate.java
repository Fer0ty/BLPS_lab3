package ru.artemiyandarina.blps_lab2.schemas.petition;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class PetitionCreate extends PetitionBase {
    @Schema(example = "RUSSIA")
    @NotBlank
    @Size(max = 20)
    String country;
}
