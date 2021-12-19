package com.flying.airports.appuser;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "AppUser")
@Data
@Table(
        name = "appuser",
        uniqueConstraints = {
                @UniqueConstraint(name = "appuser_email_unique", columnNames = "email")
        }
)
@NoArgsConstructor
public class AppUser {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    public AppUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
