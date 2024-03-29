package io.getarrays.securecapita.asserts.repo;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.StationName;
import io.getarrays.securecapita.stock.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AssertEntityRepository extends PagingAndSortingRepository<AssertEntity, Long>, ListCrudRepository<AssertEntity, Long> {

//    @Query("select  from Assert where a.stationName=:n" )
//    List<AssertEntity> getAllAssertsByStation(@Param("n")String stationName);

    @Query("SELECT a FROM AssertEntity a WHERE a.station.stationName = ?1")
    List<AssertEntity> getAllAssertsByStation( StationName stationName);



}
