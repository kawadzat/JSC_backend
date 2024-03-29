package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssertEntityService {

    List<AssertEntity> getAllAssertsByStation(String stationName);
}
