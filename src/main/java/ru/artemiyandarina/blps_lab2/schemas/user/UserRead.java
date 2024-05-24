package ru.artemiyandarina.blps_lab2.schemas.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.artemiyandarina.blps_lab2.models.Role;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRead extends UserBase {
    private Long id;
    private Role role;
}
