package ru.artemiyandarina.lab3.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import ru.artemiyandarina.lab3.exceptions.NotFoundException;
import ru.artemiyandarina.lab3.models.User;
import ru.artemiyandarina.lab3.repositories.UserRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@Profile("main")
public class JAASAuthorityGranter implements AuthorityGranter {

    private final UserRepository userRepository;
    @Override
    public Set<String> grant(Principal principal) {
        User user = userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException(principal.getName(), "User"));
        return Collections.singleton(user.getRole().name());
    }
}
