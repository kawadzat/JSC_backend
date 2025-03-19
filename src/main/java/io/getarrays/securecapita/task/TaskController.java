package io.getarrays.securecapita.task;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.roles.prerunner.AUTH_ROLE;
import io.getarrays.securecapita.roles.prerunner.ROLE_AUTH;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/task")
@RequiredArgsConstructor

public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<CustomMessage> createTask(@AuthenticationPrincipal UserDTO currentUser,
                                                    @RequestBody @Valid TaskDto taskDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> Arrays.asList(AUTH_ROLE.SECRETARY.name(),AUTH_ROLE.HEADADMIN.name(),AUTH_ROLE.HEAD_IT.name()).contains(ROLE_AUTH.READ_USER.name()))) {
            return ResponseEntity.ok(new CustomMessage("Task Created Successfully",
                    taskService.createTask(currentUser, taskDto)));

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<CustomMessage> updateTask(@AuthenticationPrincipal UserDTO currentUser,
                                                    @PathVariable Long taskId, @RequestBody @Valid TaskDto taskDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.READ_USER.name()))) {
            return ResponseEntity.ok(new CustomMessage("Task Updated Successfully",
                    taskService.updateTask(currentUser, taskId, taskDto)));

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {
        return ResponseEntity.ok().body(taskService.getTaskById(taskId));
    }

    @GetMapping()
    public ResponseEntity<?> getAllTasks(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(taskService.getAllTasks(PageRequest.of(page, size,
                Sort.by("lastModifiedDate").descending())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomMessage> deleteTask(@PathVariable("id") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok(new CustomMessage("Task deleted successfully"));
    }

    @PatchMapping("/updateStatus/{taskId}")
    public ResponseEntity<CustomMessage> updateTaskStatus(@AuthenticationPrincipal UserDTO currentUser,
                                                          @PathVariable Long taskId, @RequestParam String status) {
        taskService.updateTaskStatus(currentUser, taskId, status);
        return ResponseEntity.ok(new CustomMessage("Task status updated successfully"));
    }
}