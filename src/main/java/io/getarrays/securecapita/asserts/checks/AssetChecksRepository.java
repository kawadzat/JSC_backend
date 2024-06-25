package io.getarrays.securecapita.asserts.checks;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AssetChecksRepository extends JpaRepository<AssetCheck, Long> {

    @Query(" SELECT new io.getarrays.securecapita.asserts.checks.AssertChecksResponseDto(" +
            "a.id, " +
            "a.station.stationName, " +
            "a.checkedBy.firstName, " +
            "a.checkedBy.email, " +
            "a.updatedDate " +
            ") FROM AssetCheck a "+
            "WHERE a.station.station_id=:stationId "+
            "GROUP BY a.id"
    )
    Page<AssertChecksResponseDto> findAllChecks(@RequestParam("stationId") Long stationId, PageRequest updatedDate);
}
