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
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolesPreRunner implements CommandLineRunner {
    private final UserRepository1 userRepository1;
    private final RoleRepository1 roleRepository1;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
        List<Role> roles = roleRepository1.findAll();
        ArrayList<User> noRoleUsers = userRepository1.findUsersWithNoRoles();
        Optional<Role> role = roles.stream().filter(r -> r.getName().equals(AUTH_ROLE.USER.name())).findFirst();
        for (User user : noRoleUsers) {
            UserRole userRole = UserRole.builder().userId(user.getId()).active(true).role(role.orElseGet(() -> roles.get(0))).createdDate(new Timestamp(System.currentTimeMillis())).build();
            user.addRole(userRoleRepository.save(userRole));
        }
        userRepository1.saveAll(noRoleUsers);
    }

    public void initializeRoles() {
        List<Role> roles = roleRepository1.findAll();
        if (roles.stream().anyMatch((role -> Objects.equals(role.getName(), AUTH_ROLE.USER.name())))) {
            //runs everytime: can update
            Role role = roles.stream().filter((role1 -> Objects.equals(role1.getName(), AUTH_ROLE.USER.name()))).findFirst().get();
            role.setName(AUTH_ROLE.USER.name());
            role.setPermission(ROLE_AUTH.READ_USER.name());
            roleRepository1.save(role);
        } else {
            //runs first time
            Role roleUser = Role.builder().name(AUTH_ROLE.USER.name()).permission(ROLE_AUTH.READ_USER.name()).build();
            roleRepository1.save(roleUser);
        }
        String permissionAdmin = ROLE_AUTH.READ_USER + "," +
//                ROLE_AUTH.UPDATE_USER + "," +
                ROLE_AUTH.VIEW_ASSET + "," +
                ROLE_AUTH.VIEW_STATION + "," +
                ROLE_AUTH.ASSIGN_ROLE + "," +
                ROLE_AUTH.CREATE_PRODUCT + "," +
                ROLE_AUTH.CREATE_ASSET;
        if (roles.stream().anyMatch((role -> Objects.equals(role.getName(), AUTH_ROLE.ADMIN.name())))) {
            //runs everytime: can update
            Role role = roles.stream().filter((role1 -> Objects.equals(role1.getName(), AUTH_ROLE.ADMIN.name()))).findFirst().get();
            role.setName(AUTH_ROLE.ADMIN.name());
//            role.setPermission("READ:USER,UPDATE:USER");
            role.setPermission(
                    permissionAdmin
            );
            roleRepository1.save(role);
        } else {
            //runs first time
            Role roleUser = Role.builder().name(AUTH_ROLE.ADMIN.name()).permission(permissionAdmin).build();
            roleRepository1.save(roleUser);
        }

        String permissionSysAdmin = ROLE_AUTH.READ_USER + "," +
                ROLE_AUTH.UPDATE_USER + "," +
                ROLE_AUTH.VIEW_ASSET + "," +
                ROLE_AUTH.VIEW_STATION + "," +
                ROLE_AUTH.ALL_STATION + "," +
                ROLE_AUTH.CREATE_STATION + "," +
                ROLE_AUTH.ASSIGN_STATION + "," +
                ROLE_AUTH.ASSIGN_ROLE + "," +
                ROLE_AUTH.CREATE_PRODUCT + "," +
                ROLE_AUTH.CREATE_PURCHASEREQUEST + "," +
                ROLE_AUTH.CREATE_ASSET;
        if (roles.stream().anyMatch((role -> Objects.equals(role.getName(), AUTH_ROLE.SYSADMIN.name())))) {
            //runs everytime: can update
            Role role = roles.stream().filter((role1 -> Objects.equals(role1.getName(), AUTH_ROLE.SYSADMIN.name()))).findFirst().get();
            role.setName(AUTH_ROLE.SYSADMIN.name());
            role.setPermission(
                    permissionSysAdmin
            );
            roleRepository1.save(role);
        } else {
            //runs first time
            Role roleUser = Role.builder().name(AUTH_ROLE.SYSADMIN.name())
                    .permission(
                            permissionSysAdmin
                    ).build();
            roleRepository1.save(roleUser);
        }


        String permissionPrinciple = ROLE_AUTH.READ_USER + "," +
//                ROLE_AUTH.UPDATE_USER + "," +
                ROLE_AUTH.VIEW_ASSET + "," +
                ROLE_AUTH.VIEW_STATION + "," +
                ROLE_AUTH.ALL_STATION + "," +
                ROLE_AUTH.CREATE_STATION + "," +
                ROLE_AUTH.ASSIGN_STATION + "," +
                ROLE_AUTH.ASSIGN_ROLE + "," +
                ROLE_AUTH.CREATE_PRODUCT + "," +
                ROLE_AUTH.CREATE_ASSET;

        if (roles.stream().anyMatch((role -> Objects.equals(role.getName(), AUTH_ROLE.PRINCIPAL_ADMIN.name())))) {
            Role role = roles.stream().filter((role1 -> Objects.equals(role1.getName(), AUTH_ROLE.PRINCIPAL_ADMIN.name()))).findFirst().get();
            role.setName(AUTH_ROLE.PRINCIPAL_ADMIN.name());
            role.setPermission(
                    permissionPrinciple);
            roleRepository1.save(role);
        } else {
            Role roleUser = Role.builder().name(AUTH_ROLE.PRINCIPAL_ADMIN.name()).permission(
                    permissionPrinciple).build();
            roleRepository1.save(roleUser);
        }
    }
}