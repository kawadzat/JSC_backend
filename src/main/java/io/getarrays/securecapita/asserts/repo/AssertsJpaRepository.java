package io.getarrays.securecapita.asserts.repo;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.dto.AssetItemStat;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AssertsJpaRepository extends JpaRepository<AssertEntity, Long> {
    @Query("SELECT COUNT(a) FROM AssertEntity a WHERE LOWER(a.assertType) = 'fixed'")
    int countFixedAsserts();

    @Query("SELECT COUNT(a) FROM AssertEntity a WHERE LOWER(a.assertType) = 'fixed' AND a.station.station_id=:stationId")
    int countFixedAsserts(Long stationId);

    @Query("SELECT COUNT(a) FROM AssertEntity a WHERE LOWER(a.assertType) = 'current'")
    int countCurrentAsserts();

    @Query("SELECT COUNT(a) FROM AssertEntity a WHERE LOWER(a.assertType) = 'current' AND a.station.station_id=:stationId")
    int countCurrentAsserts(Long stationId);

    @Query("SELECT new io.getarrays.securecapita.dto.AssetItemStat(" +
            "a.assetDisc, " +
            "COUNT(a.id)) FROM AssertEntity a " +
            "GROUP BY a.assetDisc")
    ArrayList<AssetItemStat> findAssertItemStatsByAssetDisc(PageRequest pageRequest);

    @Query("SELECT new io.getarrays.securecapita.dto.AssetItemStat(" +
            "a.assetDisc, " +
            "COUNT(a.id)) FROM AssertEntity a " +
            "WHERE a.station.station_id=:stationId " +
            "GROUP BY a.assetDisc")
    ArrayList<AssetItemStat> findAssertItemStatsByAssetDisc(Long stationId);

    @Query("SELECT a FROM AssertEntity a WHERE a.assetDisc = :assetDisc")
    Optional<AssertEntity> findByName(@Param("assetDisc") String assetDisc);

    @Query("SELECT a FROM AssertEntity a WHERE a.assetDisc = :assetDisc and a.station.station_id=:selectedStationID")
    Optional<AssertEntity> findByNameAndStation(String assetDisc, Long selectedStationID);

    @Query("SELECT a FROM AssertEntity a WHERE a.serialNumber = :serialNumber and a.station.station_id=:selectedStationID")
    Optional<AssertEntity> findBySerialAndStation(String serialNumber, Long selectedStationID);

    @Query("SELECT COUNT(a) FROM AssertEntity a WHERE a.station.station_id=:stationId")
    int countAsserts(Long stationId);
}
