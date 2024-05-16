package io.getarrays.securecapita.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.roles.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Junior RT
 * @version 1.0
 * @license Get Arrays, LLC (https://getarrays.io)
 * @since 8/22/2022
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email. Please enter a valid email address")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private boolean enabled;

    @Column(name = "non_locked")
    private boolean isNotLocked = true;
    private boolean isUsingMfa;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    @Builder.Default
    private Set<UserRole> roles = new HashSet<>();

    public void addRole(UserRole role) {
        roles.add(role);
    }

    public void removeRole(UserRole role) {
        roles.remove(role);
    }

    public void expireAllRoles() {
        roles.forEach((role) -> role.setActive(false));
    }

    public void removeAllRole() {
        roles.clear();
    }
}




