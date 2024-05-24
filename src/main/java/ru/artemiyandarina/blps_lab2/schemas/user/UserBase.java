package ru.artemiyandarina.blps_lab2.schemas.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserBase {
    @Schema(example = "Иван")
    @NotBlank
    @Size(max = 30)
    protected String name;

    @Schema(example = "Иванов")
    @NotBlank
    @Size(max = 30)
    protected String surname;

    @Schema(example = "sample@example.com")
    @NotBlank
    @Email
    @Size(max = 30)
    protected String email;

}

