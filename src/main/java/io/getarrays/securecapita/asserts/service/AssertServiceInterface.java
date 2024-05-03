package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import org.springframework.http.ResponseEntity;


public interface AssertServiceInterface {


    void addInspectionToAssertEntity(Long id, Inspection inspection);
    Iterable<AssertEntity> getAsserts();
    ResponseEntity<?> getAllAssertsByStation(Long stationId);

    public Inspection getInspection(Long id) ;


    AssertEntity getAssertEntityById(Long assertEntityId);

    ResponseEntity<?> getStats();
}
