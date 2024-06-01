package ru.artemiyandarina.lab3.services.mapping;

import org.springframework.stereotype.Service;
import ru.artemiyandarina.lab3.models.User;
import ru.artemiyandarina.lab3.schemas.user.UserBase;
import ru.artemiyandarina.lab3.schemas.user.UserRead;
import ru.artemiyandarina.lab3.schemas.user.UserRegister;


@Service
public class UserMapper {
    protected User mapUserBaseToEntity(UserBase schema, User entity) {
        entity.setName(schema.getName());
        entity.setSurname(schema.getSurname());
        entity.setEmail(schema.getEmail());
        return entity;
    }

    protected User mapUserBaseToEntity(UserBase schema) {
        return mapUserBaseToEntity(schema, new User());
    }

    public User mapUserRegistrationToEntity(UserRegister schema) {
        User entity = mapUserBaseToEntity(schema);
        entity.setPassword(schema.getPassword());
        return entity;
    }

    public UserRead mapEntityToUserRead(User entity) {
        if (entity == null) return null;
        UserRead schema = new UserRead();
        schema.setId(entity.getId());
        schema.setName(entity.getName());
        schema.setSurname(entity.getSurname());
        schema.setRole(entity.getRole());
        schema.setEmail(entity.getEmail());
        return schema;
    }
}

