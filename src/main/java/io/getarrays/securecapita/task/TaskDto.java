package io.getarrays.securecapita.task;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.utils.ValidEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDto {
    private Long id;

    private String code;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private Date inititatedDate;

    @NotNull(message = "startDate is required")
    @FutureOrPresent(message = "startDate must be in the present or future")
    private Date startDate;

    @NotNull(message = "dueDate is required")
    @Future(message = "Due date must be in the future")
    private Date dueDate;

    @NotNull(message = "priority is required")
    @ValidEnum(enumClass = PriorityEnum.class, message = "Invalid priority value")
    private String priority;

    @NotNull(message = "status is required")
    @ValidEnum(enumClass = TaskStatusEnum.class, message = "Invalid status value")
    private String status;

    private UserDTO initiatedUser;

    @NotNull(message = "assignedUserId is required")
    private Long assignedUserId;

    private UserDTO assignedUser;
}
