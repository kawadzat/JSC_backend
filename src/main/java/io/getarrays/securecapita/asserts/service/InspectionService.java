package io.getarrays.securecapita.asserts.service;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.asserts.repo.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InspectionService  implements io.getarrays.securecapita.asserts.InspectionService {

    @Autowired
    public InspectionRepository inspectionRepository;
    /* to create user */
    public Inspection createInspection(Inspection newInspection) {
        Inspection createdInspection = inspectionRepository.save(newInspection);
        return createdInspection;
    }

    @Override
    public Inspection getInspectionById(Long InspectionId) {
        return null;
    }

    @Override
    public Inspection addInspectionToAssertEntity(AssertEntity assertEntity, String inspection) {


                Inspection newInspection = new Inspection();
        newInspection.setDate(new Date());
        newInspection.setRemarks(inspection);
        newInspection.setAssertEntity(assertEntity);


        List<Inspection> inspections = assertEntity.getInspections();
        inspections.add(newInspection);

           return newInspection;

    }

    /* updating the user */
    public Inspection updateInspection(Inspection inspection) {

        Inspection updatedInspection =inspectionRepository.save(inspection);

        return updatedInspection;
    }



//    @Override
//    public Inspection addInspectionToAssertEntity(AssertEntity assertEntity, String inspection) {
//        Inspection newInspection = new Inspection();
//        newInspection.setDate(new Date());
//        newInspection.setRemarks(inspection);
//        newInspection.setAssertEntity(assertEntity);
//
//        List<Inspection> inspections = assertEntity.getInspections();
//        inspections.add(newInspection);
//
//           return newInspection;
//    }




}
