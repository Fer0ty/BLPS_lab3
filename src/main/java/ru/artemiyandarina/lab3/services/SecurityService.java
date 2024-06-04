package ru.artemiyandarina.lab3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.artemiyandarina.lab3.exceptions.NotFoundException;
import ru.artemiyandarina.lab3.exceptions.PermissionDeniedException;
import ru.artemiyandarina.lab3.models.User;
import ru.artemiyandarina.lab3.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@Profile({"devMain", "heliosMain"})
public class SecurityService {
    final UserRepository userRepository;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository
                .findByEmail(auth.getName())
                .orElseThrow(
                        () -> new NotFoundException(auth.getName(), "User")
                );
    }

    public void userRequired(User user) {
        if (!getCurrentUser().getId().equals(user.getId())) {
            throw new PermissionDeniedException();
        }
    }
}
