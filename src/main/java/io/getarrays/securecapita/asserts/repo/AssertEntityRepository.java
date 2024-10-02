package io.getarrays.securecapita.asserts.repo;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.AssertResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssertEntityRepository extends PagingAndSortingRepository<AssertEntity, Long>, JpaRepository<AssertEntity, Long>, ListCrudRepository<AssertEntity, Long> {

    //    @Query("select  from Assert where a.stationName=:n" )
//    List<AssertEntity> getAllAssertsByStation(@Param("n")String stationName);
    @Query("SELECT a FROM AssertEntity a WHERE a.station.station_id = :stationId " +
            " AND (LOWER(a.assetDisc) LIKE %:query% " +
            " OR LOWER(a.assetNumber) LIKE %:query% " +
            " OR LOWER(a.serialNumber) LIKE %:query% " +
            " OR LOWER(a.invoiceNumber) LIKE %:query% " +
            " OR LOWER(a.assertType) LIKE %:query% " +
            " OR LOWER(a.location) LIKE %:query% " +
            " OR LOWER(a.officeLocation.name) LIKE %:query% " +
//            " OR a.quantity LIKE %:query% " +
            " OR LOWER(a.initialRemarks) LIKE %:query% )")
    Page<AssertEntity> getAllAssertsByStationPage(@Param("stationId") Long stationId,
                                                  @Param("query") String query,
                                                  Pageable pageRequest);

    @Query("SELECT DISTINCT a FROM AssertEntity a " +
            "JOIN a.station s " +
            "JOIN UserStation us ON us.station = s " +
            "WHERE us.user.id = :userId AND s.station_id = :stationId " +
            " AND (LOWER(a.assetDisc) LIKE %:query% " +
            " OR LOWER(a.assetNumber) LIKE %:query% " +
            " OR LOWER(a.serialNumber) LIKE %:query% " +
            " OR LOWER(a.invoiceNumber) LIKE %:query% " +
            " OR LOWER(a.assertType) LIKE %:query% " +
            " OR LOWER(a.location) LIKE %:query% " +
            " OR LOWER(a.officeLocation.name) LIKE %:query% " +
//            " OR a.quantity LIKE %:query% " +
            " OR LOWER(a.initialRemarks) LIKE %:query% )")
    Page<AssertEntity> getAssertsByUserStationPaged(
            @Param("userId") Long userId,
            @Param("stationId") Long stationId,
            @Param("query") String query,
            Pageable pageable
    );

    @Query("SELECT DISTINCT a FROM AssertEntity a " +
            "JOIN a.station s " +
            "JOIN UserStation us ON us.station = s " +
            "WHERE us.user.id = :userId AND s.station_id = :stationId " +
            " AND (:query IS NULL OR :query = '' OR (LOWER(a.assetDisc) LIKE %:query% " +
            " OR LOWER(a.assetNumber) LIKE %:query% " +
            " OR LOWER(a.serialNumber) LIKE %:query% " +
            " OR LOWER(a.invoiceNumber) LIKE %:query% " +
            " OR LOWER(a.assertType) LIKE %:query% " +
            " OR LOWER(a.location) LIKE %:query% " +
            " OR LOWER(a.officeLocation.name) LIKE %:query% " +
            " OR LOWER(a.initialRemarks) LIKE %:query% ))")
    List<AssertEntity> getAssertsByUserStation(
            @Param("userId") Long userId,
            @Param("stationId") Long stationId,
            @Param("query") String query
    );

    @Query("SELECT DISTINCT a FROM AssertEntity a " +
            "JOIN a.station s " +
            "JOIN UserStation us ON us.station = s " +
            "WHERE s.station_id = :stationId " +
            " AND (:query IS NULL OR :query = '' OR (LOWER(a.assetDisc) LIKE %:query% " +
            " OR LOWER(a.assetNumber) LIKE %:query% " +
            " OR LOWER(a.serialNumber) LIKE %:query% " +
            " OR LOWER(a.invoiceNumber) LIKE %:query% " +
            " OR LOWER(a.assertType) LIKE %:query% " +
            " OR LOWER(a.location) LIKE %:query% " +
            " OR LOWER(a.officeLocation.name) LIKE %:query% " +
            " OR LOWER(a.initialRemarks) LIKE %:query% ))")
    List<AssertEntity> getAssertsByStation(
            @Param("stationId") Long stationId,
            @Param("query") String query
    );

    //without query
    @Query("SELECT DISTINCT a FROM AssertEntity a " +
            "JOIN a.station s " +
            "JOIN UserStation us ON us.station = s " +
            "WHERE us.user.id = :userId AND s.station_id = :stationId ")
    List<AssertEntity> getAssertsByUserStation(
            @Param("userId") Long userId,
            @Param("stationId") Long stationId
    );

    @Query("SELECT DISTINCT a FROM AssertEntity a " +
            "JOIN a.station s " +
            "JOIN UserStation us ON us.station = s " +
            "WHERE s.station_id = :stationId ")
    List<AssertEntity> getAssertsByStation(
            @Param("stationId") Long stationId
    );


    @Query("SELECT new io.getarrays.securecapita.asserts.model.AssertResponseDto(a.id, a.assetDisc,a.assetNumber) FROM AssertEntity a where a.station.station_id=:stationId")
    List<AssertResponseDto> getAllAssertsByStation(Long stationId);

    @EntityGraph(value = "assert-entity-graph")
    @Query("SELECT a from AssertEntity a where a.id=?1")
    Optional<AssertEntity> findByAssetId(Long id);
}
