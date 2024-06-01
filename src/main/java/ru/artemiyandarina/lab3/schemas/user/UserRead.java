package ru.artemiyandarina.lab3.schemas.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.artemiyandarina.lab3.models.Role;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRead extends UserBase {
    private Long id;
    private Role role;
}
