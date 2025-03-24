package io.getarrays.securecapita.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatusIn(List<TaskStatusEnum> statuses, Pageable pageable);

    @Query("SELECT t FROM Task t")
    Page<Task> filterTasks(Pageable pageable);

    @Query("SELECT t FROM Task t WHERE " +
            "(:ownedByMe IS NULL OR (t.initiatedUser.id = :currentUserId AND :ownedByMe = TRUE)) AND " +
            "(:assignedToMe IS NULL OR (:currentUserId IN (SELECT u.id FROM t.assignedUsers u) AND :assignedToMe = " +
            "TRUE)) AND " +
            "(:stationId IS NULL OR EXISTS (" +
            "   SELECT us FROM UserStation us WHERE " +
            "   (us.user.id IN (SELECT u.id FROM t.assignedUsers u) OR us.user.id = t.initiatedUser.id) " +
            "   AND us.station.id = :stationId" +
            ")) AND " +
            "(:departmentId IS NULL OR EXISTS (" +
            "   SELECT u FROM t.assignedUsers u WHERE u.department.id = :departmentId) OR " +
            "   t.initiatedUser.department.id = :departmentId" +
            ")")
    Page<Task> findTasksByFilters(
            @Param("currentUserId") Long currentUserId,
            @Param("ownedByMe") Boolean ownedByMe,
            @Param("assignedToMe") Boolean assignedToMe,
            @Param("stationId") Long stationId,
            @Param("departmentId") Long departmentId,
            Pageable pageable
    );

}
