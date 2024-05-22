package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.AssertEntityRepository;
import io.getarrays.securecapita.asserts.repo.AssertsJpaRepository;
import io.getarrays.securecapita.asserts.repo.InspectionRepository;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.AssetItemStat;
import io.getarrays.securecapita.dto.AssetsStats;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import io.getarrays.securecapita.userlogs.ActionType;
import io.getarrays.securecapita.userlogs.UserLogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssertService implements AssertServiceInterface {

    private final AssertEntityRepository assertRepository;
    private final InspectionRepository inspectionRepository;
    private final StationRepository stationRepository;
    private final AssertEntityRepository assertEntityRepository;
    private final AssertsJpaRepository assertsJpaRepository;
    private final UserLogService userLogService;
    private final UserRepository1 userRepository1;

    /* updating the user */
    public AssertEntity updateAssertEntity(AssertEntity assertEntity) {

        AssertEntity updatedAssertEntity = assertRepository.save(assertEntity);
        userLogService.addLog(ActionType.UPDATED, "updated assert successfully.");
        return updatedAssertEntity;
    }


    /* to create user */
    public ResponseEntity<?> createAssert(AssertEntity newAssert) throws Exception {
        //check duplicate
        Optional<AssertEntity> duplicatedAssert = assertsJpaRepository.findBySerialAndStation(newAssert.getSerialNumber(), newAssert.getSelectedStationID());
        if (duplicatedAssert.isPresent()) {
            return ResponseEntity.status(422).body(new CustomMessage("Found Duplicate Entry. Please check again."));
        }
        Optional<Station> optionalStation = stationRepository.findById(newAssert.getSelectedStationID());
        if (optionalStation.isEmpty()) {
            throw new Exception("Station not found");
        }
        newAssert.setStation(optionalStation.get());
        User user = userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
        newAssert.setPreparedBy(user);
        AssertEntity createdAssert = assertRepository.save(newAssert);
        userLogService.addLog(ActionType.CREATED, "created assert successfully. Assert: " + newAssert.getAssetDisc());
        return ResponseEntity.ok(createdAssert);
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
        userLogService.addLog(ActionType.UPDATED, "added inspection to assert.");

    }

    public Inspection getInspection(Long id) {
        return inspectionRepository.findById(id).get();
    }

    @Override
    public AssertEntity getAssertEntityById(Long assertEntityId) {
        return null;
    }


    public Iterable<AssertEntity> getAsserts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("date"));

        return assertRepository.findAll(pageRequest);
    }

    @Override
    public ResponseEntity<?> getAllAssertsByStation(Long stationId, PageRequest pageRequest) {
        return ResponseEntity.ok(assertRepository.getAllAssertsByStationPage(stationId, pageRequest));
    }

    @Override
    public ResponseEntity<?> getStats(PageRequest pageRequest) {
        // Fetch the total fixed asserts and total current asserts
        int totalFixedAsserts = assertsJpaRepository.countFixedAsserts();
        int totalCurrentAsserts = assertsJpaRepository.countCurrentAsserts();

        // Fetch asset statistics
        ArrayList<AssetItemStat> assetsStats = assertsJpaRepository.findAssertItemStatsByAssetDisc(pageRequest);
        userLogService.addLog(ActionType.VIEW, "checked stats of asserts.");

        // Return a new Stats object with the calculated totals and fetched asset statistics
        return ResponseEntity.ok(AssetsStats.builder()
                .totalAsserts(assertsJpaRepository.count())
                .totalFixedAsserts(totalFixedAsserts)
                .totalCurrentAsserts(totalCurrentAsserts)
                .assetsStats(assetsStats)
                .build());
    }

    public AssetsStats getStats(Long stationId) {
        // Fetch the total fixed asserts and total current asserts
        int totalFixedAsserts = assertsJpaRepository.countFixedAsserts(stationId);
        int totalCurrentAsserts = assertsJpaRepository.countCurrentAsserts(stationId);

        // Fetch asset statistics
        ArrayList<AssetItemStat> assetsStats = assertsJpaRepository.findAssertItemStatsByAssetDisc(stationId);
        userLogService.addLog(ActionType.VIEW, "checked stats of asserts.");

        // Return a new Stats object with the calculated totals and fetched asset statistics
        return AssetsStats.builder()
                .totalAsserts(assertsJpaRepository.countAsserts(stationId))
                .totalFixedAsserts(totalFixedAsserts)
                .totalCurrentAsserts(totalCurrentAsserts)
                .assetsStats(assetsStats)
                .build();
    }
    public ResponseEntity<?> getAssertsForOwnStation(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("date"));
        User user = userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
        if (user.getStation() != null) {
            return getAllAssertsByStation(user.getStation().getStation_id(), pageRequest);
        }
        return ResponseEntity.ok(new ArrayList<AssertEntity>());
    }

    public List<AssertEntity> getAsserts() {
        return assertsJpaRepository.findAll();
    }
}
