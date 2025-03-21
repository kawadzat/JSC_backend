package io.getarrays.securecapita.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatusIn(List<TaskStatusEnum> statuses, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE " +
            "(:ownedByMe IS NULL OR (t.initiatedUser.id = :currentUserId AND :ownedByMe = TRUE)) AND " +
            "(:assignedToMe IS NULL OR (t.assignedUser.id = :currentUserId AND :assignedToMe = TRUE))")
    Page<Task> findTasksByFilters(
            @Param("currentUserId") Long currentUserId,
            @Param("ownedByMe") Boolean ownedByMe,
            @Param("assignedToMe") Boolean assignedToMe,
            Pageable pageable
    );
}
