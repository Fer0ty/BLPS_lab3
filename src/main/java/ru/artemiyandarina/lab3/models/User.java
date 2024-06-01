package ru.artemiyandarina.lab3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "person")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String surname;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    public void setPassword(String password) {
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
    }
}
