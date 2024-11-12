package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.dto.AssetSearchCriteriaDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AssertServiceInterface {


    void addInspectionToAssertEntity(Long id, Inspection inspection);

    ResponseEntity<?> getAllAssertsByStation(Long userId,Long stationId, String query,PageRequest pageRequest);

    public Inspection getInspection(Long id);


    AssertEntity getAssertEntityById(Long assertEntityId);

    ResponseEntity<?> getAllAssertsByUserStation(Long userId, Long stationId, String query,PageRequest pageRequest);

    ResponseEntity<?> getAllAssertsByUserStation(Long userId, String query,PageRequest pageRequest);

    ResponseEntity<?> getStats();

    List<AssertEntity> searchAsserts(AssetSearchCriteriaDTO criteria);
}
