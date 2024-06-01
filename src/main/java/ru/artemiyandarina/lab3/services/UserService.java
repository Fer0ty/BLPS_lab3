package ru.artemiyandarina.lab3.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.artemiyandarina.lab3.exceptions.NotFoundException;
import ru.artemiyandarina.lab3.models.User;
import ru.artemiyandarina.lab3.repositories.UserRepository;
import ru.artemiyandarina.lab3.repositories.XMLUserRepository;
import ru.artemiyandarina.lab3.schemas.user.UserRead;
import ru.artemiyandarina.lab3.schemas.user.UserRegister;
import ru.artemiyandarina.lab3.services.mapping.UserMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    final XMLUserRepository xmlUserRepository;
    public UserRead register(UserRegister schema) {
        User newUser = userMapper.mapUserRegistrationToEntity(schema);
        newUser = userRepository.save(newUser);
        xmlUserRepository.save(newUser);
        return userMapper.mapEntityToUserRead(newUser);
    }

    public UserRead getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "User"));

        return userMapper.mapEntityToUserRead(user);
    }

    public List<UserRead> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapEntityToUserRead)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "User"));
        userRepository.delete(user);
        xmlUserRepository.delete(user);
    }
}
