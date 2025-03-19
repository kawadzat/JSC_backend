package io.getarrays.securecapita.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.itauditing.Auditable;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Builder
@Entity
@NoArgsConstructor
@Table(name = "`task`")
public class Task extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private String title;

    private String description;

    private Date inititatedDate;

    private Date startDate;

    private Date dueDate;

    @Column
    @Enumerated(EnumType.STRING)
    @Nonnull
    private PriorityEnum priority;

    @Column
    @Enumerated(EnumType.STRING)
    @Nonnull
    private TaskStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "initiated_user_id", nullable = false)
    private User initiatedUser;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id", nullable = false)
    private User assignedUser;
}