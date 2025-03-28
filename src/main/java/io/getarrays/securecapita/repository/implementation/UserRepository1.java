package io.getarrays.securecapita.repository.implementation;

import io.getarrays.securecapita.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository1 extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("Select u FROM User u where u.roles IS EMPTY")
    ArrayList<User> findUsersWithNoRoles();

    @Query("SELECT u FROM User u WHERE u.verificationToken = :token AND u.verificationTokenExpiry > CURRENT_TIMESTAMP" +
            "()")
    Optional<User> findByPasswordVerificationToken(String token);

    @Query("SELECT u FROM User u " +
            "JOIN u.roles ur " +
            "JOIN ur.role r " +
            "JOIN u.stations us " +
            "WHERE us.station.station_id = :stationId " +
            "AND r.name IN :roleNames")
    List<User> findByStationIdAndRoleNameIn(Long stationId, List<String> roleNames);

    @Query("SELECT u FROM User u " +
            "JOIN u.roles ur " +
            "JOIN ur.role r " +
            "JOIN u.stations us " +
            "JOIN u.userDepartments ud " +  // Adjusted for UserDepartment
            "WHERE ud.department.id = :departmentId " +  // Updated department reference
            "AND us.station.station_id IN :stationIds " +
            "AND r.name IN :roleNames")
    List<User> findByDepartmentIdAndStationIdInAndRoleNameIn(Long departmentId, List<Long> stationIds,
                                                             List<String> roleNames);


    @Query("SELECT u FROM User u " +
            "JOIN u.stations us " +
            "JOIN u.userDepartments ud " +  // Adjusted for UserDepartment
            "WHERE ud.department.id IN :departmentIds " +  // Updated department reference
            "AND us.station.station_id IN :stationIds")
    List<User> findByDepartmentIdAndStationIdIn(List<Long> departmentIds, List<Long> stationIds);

}
