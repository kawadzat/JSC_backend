package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.asserts.model.StationName;
import io.getarrays.securecapita.asserts.repo.AssertEntityRepository;
import io.getarrays.securecapita.asserts.repo.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssertService  implements  AssertServiceInterface{


    @Autowired
  public AssertEntityRepository assertRepository;

    @Autowired
    public InspectionRepository inspectionRepository;



    /* updating the user */
    public AssertEntity updateAssertEntity(AssertEntity assertEntity) {

        AssertEntity updatedAssertEntity =assertRepository.save(assertEntity);

        return updatedAssertEntity;
    }


    /* to create user */
    public AssertEntity createAssert(AssertEntity newAssert) {
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
            inspection.  setAssertEntity(assertEntity);
            inspectionRepository.save(inspection);
        }
    }

    public Inspection getInspection(Long id) {
        return  inspectionRepository .findById(id).get();
    }

    @Override
    public AssertEntity getAssertEntityById(Long assertEntityId) {
        return null;
    }


    public  Iterable  <AssertEntity> getAsserts() {
        return assertRepository.findAll();
    }

    @Override
    public List<AssertEntity> getAllAssertsByStation(StationName stationName) {
        return assertRepository.getAllAssertsByStation(stationName);
    }




}
