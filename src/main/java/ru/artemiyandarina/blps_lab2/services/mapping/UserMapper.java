package ru.artemiyandarina.blps_lab2.services.mapping;

import org.springframework.stereotype.Service;
import ru.artemiyandarina.blps_lab2.models.User;
import ru.artemiyandarina.blps_lab2.schemas.user.UserRegister;
import ru.artemiyandarina.blps_lab2.schemas.user.UserRead;
import ru.artemiyandarina.blps_lab2.schemas.user.UserBase;


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

