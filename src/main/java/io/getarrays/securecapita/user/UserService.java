package io.getarrays.securecapita.user;

import io.getarrays.securecapita.department.model.DepartmentEntity;
import io.getarrays.securecapita.department.service.DepartmentService;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.dtomapper.UserDTOMapper;
import io.getarrays.securecapita.repository.UserRepository;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserRepository1 userRepository1;

    public UserDTO update(Long userId, UserUpdateDto userUpdateDto) {
        User user = userRepository.get(userId);
        if (userUpdateDto.getDepartmentId() != null) {
            DepartmentEntity department = departmentService.findDepartmentByIdOrThrow(userUpdateDto.getDepartmentId());
            user.setDepartment(department);
        } else {
            user.setDepartment(null);
        }
        return UserDTOMapper.fromUser(userRepository1.save(user));
    }

    public List<UserDTO> getDepartmentAndStationFellows(UserDTO currentUser) {
        User user = userRepository.get(currentUser.getId());
        return userRepository1.findByDepartmentIdAndStationIdIn(user.getDepartment() != null ?
                user.getDepartment().getId() : null, user.getStations() != null ?
                user.getStations().stream().map(e -> e.getStation().getStation_id()).toList() : null).stream().map(UserDTOMapper::fromUser).toList();
    }

    public List<UserDTO> filterUsers(Long stationId, Long departmentId) {
        return userRepository1.findByDepartmentIdAndStationIdIn(departmentId, stationId != null ? List.of(stationId)
                : null).stream().map(UserDTOMapper::fromUser).toList();
    }
}
