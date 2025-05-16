package io.getarrays.securecapita.itinventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.getarrays.securecapita.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Builder
@Entity
@NoArgsConstructor
@NamedEntityGraph(name = "laptop-entity-graph")
@Table(name = "`laptop`")
public class Laptop {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String title;

        private String description;


        private Date issueDate;

        private Date replacementDate;

    @NotNull
    private String serialNumber;
    //add all details, station, and only can filter from frontend
    //CAN WE DO IT task filters
    @ManyToOne
    private User issuedBy;

    @ManyToMany
    @JoinTable(name = "laptop_assigned_users", joinColumns = @JoinColumn(name = "laptop_id"), inverseJoinColumns =
    @JoinColumn(name = "user_id"))
    private Set<User> users;

}
