package io.getarrays.securecapita.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.getarrays.securecapita.ProjectManagement.Issue;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.roles.UserRole;
import io.getarrays.securecapita.stationsassignment.UserStation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import java.util.*;

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



    private int projectSize;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Issue> assignedIssues = new ArrayList<>();

    @Column(name = "non_locked")
    private boolean isNotLocked = true;
    private boolean isUsingMfa;
    private LocalDateTime createdAt;
    @JsonIgnore
    private Timestamp verificationTokenExpiry;
    @JsonIgnore
    private String verificationToken;
//    @ManyToOne
//    @JoinColumn(name = "station_id")
//    private Station station;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Builder.Default
    @JsonIgnore
    private List<UserStation> stations = new ArrayList<>();
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

    public boolean isStationAssigned(Long stationId) {
        Hibernate.initialize(getStations());
        return stations.stream()
                .anyMatch(userStation -> userStation.getStation().getStation_id().equals(stationId));
    }

    //is stations empty
    public boolean isStationAssigned() {
        Hibernate.initialize(getStations());
        return !stations.isEmpty();
    }
}




