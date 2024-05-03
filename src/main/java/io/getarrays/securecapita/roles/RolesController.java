package io.getarrays.securecapita.roles;

import com.twilio.http.Response;
import io.getarrays.securecapita.domain.Role;
import io.getarrays.securecapita.repository.RoleRepository;
import io.getarrays.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/roles")
@RequiredArgsConstructor
public class RolesController {
    private final RoleRepository<Role> roleRoleRepository;
    private final UserService userService;
    @GetMapping("/all")
    public ResponseEntity<Object> getAllRoles(){
        return ResponseEntity.ok(roleRoleRepository.list());
    }

    @GetMapping("/update")
    public ResponseEntity<?> updateRole(@RequestParam("userId") Long userId,@RequestParam("role") String role){
        return userService.changeRole(userId,role);
    }
}
