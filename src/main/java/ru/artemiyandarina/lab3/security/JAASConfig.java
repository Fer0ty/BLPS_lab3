package ru.artemiyandarina.lab3.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import ru.artemiyandarina.lab3.repositories.UserRepository;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Profile("main")
public class JAASConfig {
    private final UserRepository userRepository;

    @Bean
    public InMemoryConfiguration configuration() {
        AppConfigurationEntry configEntry = new AppConfigurationEntry(JAASLoginModule.class.getName(),
                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                Map.of("userRepository", userRepository));
        var configurationEntries = new AppConfigurationEntry[]{configEntry};
        return new InMemoryConfiguration(Map.of("SPRINGSECURITY", configurationEntries));
    }

    @Bean
    public AbstractJaasAuthenticationProvider jaasAuthenticationProvider(javax.security.auth.login.Configuration configuration) {
        var provider = new DefaultJaasAuthenticationProvider();
        provider.setConfiguration(configuration);
        provider.setAuthorityGranters(new AuthorityGranter[]{new JAASAuthorityGranter(userRepository)});
        return provider;
    }
}
