package io.getarrays.securecapita.service.implementation;

import com.twilio.http.Response;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.domain.Role;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.form.UpdateForm;
import io.getarrays.securecapita.repository.RoleRepository;
import io.getarrays.securecapita.repository.UserRepository;
import io.getarrays.securecapita.repository.UserRoleRepository;
import io.getarrays.securecapita.repository.implementation.RoleRepository1;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import io.getarrays.securecapita.roles.UserRole;
import io.getarrays.securecapita.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;

import static io.getarrays.securecapita.dtomapper.UserDTOMapper.fromUser;

/**
 * @author Junior RT
 * @version 1.0
 * @license Get Arrays, LLC (https://getarrays.io)
 * @since 8/28/2022
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository1 userRepository1;
    private final UserRepository<User> userRepository;
    private final RoleRepository<Role> roleRoleRepository;
    private final RoleRepository1 roleRepository1;
    private final UserRoleRepository userRoleRepository;

    @Override
    public boolean deleteUser(Long id) {

        return userRepository.delete(id);
    }
//    @Override
//    public int getNumberOfpgaes(int pageSize) {
////        int totalNumberOfItems = size; // Replace with the actual total number of items
////        int numberOfPages = (int) Math.ceil((double) totalNumberOfItems / pageSize);
//        return userRepository.getNumberOfPages(pageSize);
//    }

    @Override
    public UserDTO createUser(User user) {
        List<Role> roles = roleRepository1.findAll();
        Optional<Role> role = roles.stream().filter(r -> r.getName().equals("ROLE_USER")).findFirst();
        UserRole userRole = UserRole.builder().active(true).role(role.orElseGet(() -> roles.get(0))).createdDate(new Timestamp(System.currentTimeMillis())).build();
        user.addRole(userRole);
        return mapToUserDTO(userRepository.create(user));
    }


    @Override
    public UserDTO listUsers() {


        return mapToUserDTO((User) userRepository.findAll());
    }


    @Override
    public Collection<UserDTO> list() {
        return maptoUserDTOList(userRepository.list());
    }

    private Collection<UserDTO> maptoUserDTOList(Collection<User> users) {
        Collection<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user -> {
            userDTOS.add(fromUser(user, roleRoleRepository.getRoleByUserId(user.getId())));
        });
        return userDTOS;
    }


    @Override
    public UserDTO getUserByEmail(String email) {
        return mapToUserDTO(userRepository.getUserByEmail(email));
    }

    @Override
    public void sendVerificationCode(UserDTO user) {
        userRepository.sendVerificationCode(user);
    }

    @Override
    public UserDTO verifyCode(String email, String code) {
        return mapToUserDTO(userRepository.verifyCode(email, code));
    }

    @Override
    @Transactional
    public ResponseEntity<?> changeRole(Long userId, String role) {
        System.out.println(role);
        Optional<Role> roleOptional = roleRepository1.findByRoleName(role);
        if (roleOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new CustomMessage("Role is invalid."));
        }
        Optional<User> userOptional = userRepository1.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new CustomMessage("UserId is invalid."));
        }
        User user = userOptional.get();
        user.expireAllRoles();
        userRepository1.save(user);
        user.removeAllRole();
        UserRole userRole=UserRole.builder().active(true).createdDate(new Timestamp(System.currentTimeMillis())).role(roleOptional.get()).build();
        user.addRole(userRole);
        userRepository1.save(user);
        return ResponseEntity.ok(new CustomMessage("Role updated for User: "+user.getFirstName()));
    }


    @Override
    public void resetPassword(String email) {
        userRepository.resetPassword(email);
    }

    @Override
    public UserDTO verifyPasswordKey(String key) {
        return mapToUserDTO(userRepository.verifyPasswordKey(key));
    }

    @Override
    public void renewPassword(String key, String password, String confirmPassword) {
        userRepository.renewPassword(key, password, confirmPassword);
    }

    @Override
    public UserDTO verifyAccountKey(String key) {
        return mapToUserDTO(userRepository.verifyAccountKey(key));
    }

    @Override
    public UserDTO updateUserDetails(UpdateForm user) {
        return mapToUserDTO(userRepository.updateUserDetails(user));
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return mapToUserDTO(userRepository.get(userId));


    }


    @Override
    public void updatePassword(Long id, String currentPassword, String newPassword, String confirmNewPassword) {
        userRepository.updatePassword(id, currentPassword, newPassword, confirmNewPassword);
    }


//    public void assignStationToUser(Long userId, Integer stationId) {
//        userRepository1.addStationToUser(userId, stationId);
//    }

    @Override
    public void updateUserRole(Long userId, String roleName) {
        roleRoleRepository.updateUserRole(userId, roleName);
    }

    @Override
    public void updateAccountSettings(Long userId, Boolean enabled, Boolean notLocked) {
        userRepository.updateAccountSettings(userId, enabled, notLocked);
    }

    @Override
    public UserDTO toggleMfa(String email) {
        return mapToUserDTO(userRepository.toggleMfa(email));
    }

    @Override
    public void updateImage(UserDTO user, MultipartFile image) {
        userRepository.updateImage(user, image);
    }

    @Override
    public void addStationToUser(Long id, Station station) {


    }

    private UserDTO mapToUserDTO(User user) {
        return fromUser(user, roleRoleRepository.getRoleByUserId(user.getId()));
    }
}
















