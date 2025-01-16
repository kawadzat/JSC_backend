package io.getarrays.securecapita.repository.implementation;

import io.getarrays.securecapita.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository1   extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query("Select u FROM User u where u.roles IS EMPTY")
    ArrayList<User> findUsersWithNoRoles();

    @Query("SELECT u FROM User u WHERE u.verificationToken = :token AND u.verificationTokenExpiry > CURRENT_TIMESTAMP()")
    Optional<User> findByPasswordVerificationToken(String token);

}
