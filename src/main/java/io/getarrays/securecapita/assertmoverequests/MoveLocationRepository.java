package io.getarrays.securecapita.assertmoverequests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface MoveLocationRepository extends JpaRepository<AssertMoveRequest, Long> {
    @Query("Select a from AssertMoveRequest a where a.assertEntity.station.station_id=:stationId")
    Page<AssertMoveRequest> findByStationId(Long stationId, PageRequest pageRequest);

//    @Query("Select o from OfficeLocation o where o.station.station_id=:stationId AND o.name=:location")
//    Optional<OfficeLocation> findByStationAndName(Long stationId, String location);
//
//    @Query("Select o from OfficeLocation o where o.station.station_id=:stationId")
//    ArrayList<OfficeLocation> findByStationId(Long stationId);

}

