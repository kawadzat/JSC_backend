package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.AssertEntityRepository;
import io.getarrays.securecapita.asserts.repo.AssertsJpaRepository;
import io.getarrays.securecapita.asserts.repo.InspectionRepository;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import io.getarrays.securecapita.dto.AssetItemStat;
import io.getarrays.securecapita.dto.Stats;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssertService implements AssertServiceInterface {

    private final AssertEntityRepository assertRepository;
    private final InspectionRepository inspectionRepository;
    private final StationRepository stationRepository;
    private final AssertEntityRepository assertEntityRepository;
    private final AssertsJpaRepository assertsJpaRepository;

    /* updating the user */
    public AssertEntity updateAssertEntity(AssertEntity assertEntity) {

        AssertEntity updatedAssertEntity = assertRepository.save(assertEntity);

        return updatedAssertEntity;
    }


    /* to create user */
    public AssertEntity createAssert(AssertEntity newAssert) throws Exception {
        Optional<Station> optionalStation = stationRepository.findById(newAssert.getSelectedStationID());
        if (optionalStation.isEmpty()) {
            throw new Exception("Station not found");
        }
        newAssert.setStation(optionalStation.get());
        AssertEntity createdAssert = assertRepository.save(newAssert);
        return createdAssert;
    }


//    @Override
//    public void addInspectionToAseertEnity(Long id, Inspection inspection) {
//        AssertEntity assertEntity = assertRepository.findById(id).orElse(null);
//
//        if (assertEntity != null) {
//            inspection.getAssertEntity(assertEntity);
//            inspectionRepository.save(inspection);
//        }
//    }

    @Override
    public void addInspectionToAssertEntity(Long id, Inspection inspection) {
        Optional<AssertEntity> assertEntityOptional = assertRepository.findById(id);

        if (assertEntityOptional.isPresent()) {
            AssertEntity assertEntity = assertEntityOptional.get();
            inspection.setAssertEntity(assertEntity);
            inspectionRepository.save(inspection);
        }
    }

    public Inspection getInspection(Long id) {
        return inspectionRepository.findById(id).get();
    }

    @Override
    public AssertEntity getAssertEntityById(Long assertEntityId) {
        return null;
    }


    public Iterable<AssertEntity> getAsserts() {
        return assertRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getAllAssertsByStation(Long stationId) {
        return ResponseEntity.ok(assertRepository.getAllAssertsByStation(stationId));
    }

    @Override
    public ResponseEntity<?> getStats() {
        // Fetch the total fixed asserts and total current asserts
        int totalFixedAsserts = assertsJpaRepository.countFixedAsserts();
        int totalCurrentAsserts = assertsJpaRepository.countCurrentAsserts();

        // Fetch asset statistics
        ArrayList<AssetItemStat> assetsStats = assertsJpaRepository.findAssertItemStatsByAssetDisc();

        // Return a new Stats object with the calculated totals and fetched asset statistics
        return ResponseEntity.ok(Stats.builder()
                .totalAsserts(assertsJpaRepository.count())
                .totalFixedAsserts(totalFixedAsserts)
                .totalCurrentAsserts(totalCurrentAsserts)
                .assetsStats(assetsStats)
                .build());
    }

}
