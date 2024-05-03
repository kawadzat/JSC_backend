package io.getarrays.securecapita.roles.prerunner;

import io.getarrays.securecapita.domain.Role;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.repository.UserRoleRepository;
import io.getarrays.securecapita.repository.implementation.RoleRepository1;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import io.getarrays.securecapita.roles.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolesPreRunner implements CommandLineRunner {
    private final UserRepository1 userRepository1;
    private final RoleRepository1 roleRepository1;
    @Override
    public void run(String... args) throws Exception {
        //provide basic role to user with empty roles:
        ArrayList<User> noRoleUsers = userRepository1.findUsersWithNoRoles();
        List<Role> roles = roleRepository1.findAll();
        Optional<Role> role = roles.stream().filter(r -> r.getName().equals("ROLE_USER")).findFirst();
        for (User user : noRoleUsers) {
            UserRole userRole = UserRole.builder().active(true).role(role.orElseGet(() -> roles.get(0))).createdDate(new Timestamp(System.currentTimeMillis())).build();
            user.addRole(userRole);
        }
        userRepository1.saveAll(noRoleUsers);
    }
}
