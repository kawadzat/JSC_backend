package io.getarrays.securecapita.asserts.repo;

import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.AssetItemStat;
import io.getarrays.securecapita.dto.StationItemStat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s FROM Station s JOIN s.users u WHERE u.id = :userId")
    List<Station> findAllStation(Long userId);


    @Transactional
    @Modifying
    @Query("UPDATE AssertEntity ae SET ae.checkedBy = :checkedBy WHERE ae.station = :station")
    void editCheckAllAssetsForStation(@Param("station") Station station, @Param("checkedBy") User checkedBy);


    @Query("SELECT new io.getarrays.securecapita.dto.StationItemStat(" +
            "a.station.station_id, "+
            "a.station.stationName, " +
            "COUNT(a.id)) FROM AssertEntity a " +
            "GROUP BY a.station.station_id")
    ArrayList<StationItemStat> findAssertItemStatsByAssetDisc(PageRequest pageRequest);
}