package io.getarrays.securecapita.asserts.repo;

import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.model.StationsAssetsStatDto;
import io.getarrays.securecapita.asserts.model.StationsStatDto;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.StationItemStat;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s FROM Station s JOIN s.users u WHERE u.id = :userId")
    List<Station> findAllStation(Long userId);


    @Transactional
    @Modifying
    @Query("UPDATE AssertEntity ae SET ae.checkedBy = :checkedBy WHERE ae.station = :station")
    void editCheckAllAssetsForStation(@Param("station") Station station, @Param("checkedBy") User checkedBy);


    @Query("SELECT new io.getarrays.securecapita.dto.StationItemStat(" +
            "a.station.station_id, " +
            "a.station.stationName, " +
            "COUNT(a.id)) FROM AssertEntity a " +
            "GROUP BY a.station.station_id")
    ArrayList<StationItemStat> findAssertItemStatsByAssetDisc();

    @Query("SELECT new io.getarrays.securecapita.dto.StationItemStat(" +
            "a.station.station_id, " +
            "a.station.stationName, " +
            "COUNT(a.id)) FROM AssertEntity a " +
            "WHERE a.station.station_id=:stationId "+
            "GROUP BY a.station.station_id")
    ArrayList<StationItemStat> findAssertItemStatsByAssetDisc(long stationId);
    @Query("SELECT NEW io.getarrays.securecapita.asserts.model.StationsStatDto(s.stationName, COUNT(DISTINCT a.id), COUNT(DISTINCT l.id))\n" +
            "FROM Station s\n" +
            "LEFT JOIN s.asserts a ON true\n" +
            "LEFT JOIN s.locations l ON true\n" +
            "GROUP BY s.stationName")
    List<StationsStatDto> getStatforAllStations();

    @Query("SELECT NEW io.getarrays.securecapita.asserts.model.StationsStatDto(s.stationName, COUNT(DISTINCT a.id), COUNT(DISTINCT l.id))\n" +
            "FROM Station s\n" +
            "LEFT JOIN s.asserts a ON true\n" +
            "LEFT JOIN s.locations l ON true\n" +
            "Where s.station_id=:stationId\n" +
            "GROUP BY s.stationName")
    List<StationsStatDto> getStatforStation(Long stationId);

    //    @Query("SELECT NEW io.getarrays.securecapita.asserts.model.StationsAssetsStatDto(a.assetDisc, a.station.stationName, COUNT(DISTINCT a.assetDisc) AS count) " +
//            "FROM AssertEntity a " +
//            "GROUP BY a.station.stationName")
    @Query("SELECT NEW io.getarrays.securecapita.asserts.model.StationsAssetsStatDto(a.assetDisc, s.stationName, COUNT(DISTINCT a.id)) " +
            "FROM Station s " +
            "JOIN s.asserts a ON true " +
            "GROUP BY a.assetDisc,s.stationName")
    List<StationsAssetsStatDto> getAllAssetsStats();


    //    @Query("SELECT NEW io.getarrays.securecapita.asserts.model.StationsAssetsStatDto(a.assetDisc, a.station.stationName, COUNT(DISTINCT a.assetDisc) AS count) " +
//            "FROM AssertEntity a " +
//            "WHERE a.station.station_id = :stationId " +
//            "GROUP BY a.station.stationName")
    @Query("SELECT NEW io.getarrays.securecapita.asserts.model.StationsAssetsStatDto(a.assetDisc, s.stationName, COUNT(DISTINCT a.id)) " +
            "FROM Station s " +
            "JOIN s.asserts a ON true " +
            "WHERE s.station_id = :stationId " +
            "GROUP BY a.assetDisc,s.stationName")
    List<StationsAssetsStatDto> getAssetsStatsForStation(@Param("stationId") Long stationId);


}