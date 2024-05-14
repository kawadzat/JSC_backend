package io.getarrays.securecapita.roles;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.getarrays.securecapita.domain.Role;
import io.getarrays.securecapita.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private Boolean active;

    private Timestamp createdDate;

}
