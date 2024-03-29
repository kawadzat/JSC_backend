package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.asserts.model.StationName;
import io.getarrays.securecapita.domain.Page;
import org.modelmapper.internal.util.Assert;

import java.util.List;


public interface AssertServiceInterface {


    void addInspectionToAssertEntity(Long id, Inspection inspection);
    Iterable<AssertEntity> getAsserts();
    List<AssertEntity> getAllAssertsByStation(StationName stationName);

    public Inspection getInspection(Long id) ;


    AssertEntity getAssertEntityById(Long assertEntityId);
}
