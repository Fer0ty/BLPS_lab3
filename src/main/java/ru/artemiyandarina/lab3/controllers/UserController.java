package ru.artemiyandarina.lab3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.artemiyandarina.lab3.schemas.user.UserRead;
import ru.artemiyandarina.lab3.schemas.user.UserRegister;
import ru.artemiyandarina.lab3.services.UserService;

import java.util.List;

@Tag(name = "Пользователи")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
@Profile({"devMain","heliosMain"})

public class UserController {
    final UserService userService;

    @Operation(summary = "Регистрирует пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserRead registerUser(@RequestBody @Valid UserRegister schema) {
        return userService.register(schema);
    }

    @Operation(summary = "Список пользователей")
    @GetMapping
    public List<UserRead> getUserList() {
        return userService.getAll();
    }

    @Operation(summary = "Возвращает пользователя")
    @GetMapping("/{id}")
    public UserRead getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Удаляет пользователя")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}

