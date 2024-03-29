package io.getarrays.securecapita.repository.implementation;

import io.getarrays.securecapita.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository1   extends JpaRepository<User,Long> {
    @Modifying
    @Query(value = "UPDATE users SET station_id = :stationId WHERE id = :userId", nativeQuery = true)
    int addStationToUser(@Param("userId") Long userId, @Param("stationId") Integer stationId);
}
