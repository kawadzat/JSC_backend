package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;


public interface AssertServiceInterface {


    void addInspectionToAssertEntity(Long id, Inspection inspection);

    ResponseEntity<?> getAllAssertsByStation(Long userId,Long stationId, PageRequest pageRequest);

    public Inspection getInspection(Long id);


    AssertEntity getAssertEntityById(Long assertEntityId);

    ResponseEntity<?> getAllAssertsByUserStation(Long userId, Long stationId, PageRequest pageRequest);

    ResponseEntity<?> getAllAssertsByUserStation(Long userId, PageRequest pageRequest);

    ResponseEntity<?> getStats();
}
