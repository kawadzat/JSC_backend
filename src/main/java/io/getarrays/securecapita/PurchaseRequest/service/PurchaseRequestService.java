package io.getarrays.securecapita.PurchaseRequest.service;

import io.getarrays.securecapita.PurchaseRequest.entity.PurchaseRequestItemEntity;
import io.getarrays.securecapita.PurchaseRequest.repository.PurchaseRequestProductRepo;
import io.getarrays.securecapita.PurchaseRequest.repository.PurchaseRequestRepo;
import io.getarrays.securecapita.PurchaseRequest.entity.PurchaseRequestEntity;
import io.getarrays.securecapita.asserts.repo.AssertsJpaRepository;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import io.getarrays.securecapita.exception.CustomMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseRequestService implements PurchaseRequestInterface {
    private final StationRepository stationRepository;
    private final PurchaseRequestProductRepo purchaseRequestProductRepo;

    private final AssertsJpaRepository assertsJpaRepository;
    private final PurchaseRequestRepo purchaseRequestRepo;


    public PurchaseRequestEntity getPurchaseRequestById(Long purchaseRequestId) {


        Optional<PurchaseRequestEntity> optionalpurchaseRequest = purchaseRequestRepo.findById(purchaseRequestId);

        boolean isPresent = optionalpurchaseRequest.isPresent();

        if (isPresent) {

            PurchaseRequestEntity purchaseRequest = optionalpurchaseRequest.get();
            return purchaseRequest;
        }

        return null;

    }


//public PurchaseRequest createPurchaseRequest(PurchaseRequest purchaseRequest) {
//
//    PurchaseRequest createdPurchaseRequest = purchaseRequestRepo.save(purchaseRequest);
//
//    return createdPurchaseRequest;
//}


    public List<PurchaseRequestEntity> getAllPurchaseRequests() {
        List<PurchaseRequestEntity> allPurchaseRequests = purchaseRequestRepo.findAll();
        return allPurchaseRequests;
    }
    /* get specific user based on Id */

//SA


    public void deletePurchaseRequest(Long purchaseRequestId) {
        purchaseRequestRepo.deleteById(purchaseRequestId);
    }

    @Override
    public void addProductsToPurchaseRequest(Long id, PurchaseRequestItemEntity purchaseRequestProduct) {


        Optional<PurchaseRequestEntity> purchaseRequestOptional = purchaseRequestRepo.findById(id);

        if (purchaseRequestOptional.isPresent()) {
            PurchaseRequestEntity purchaseRequest = purchaseRequestOptional.get();
            purchaseRequestProduct.setPurchaseRequest(purchaseRequest);
            purchaseRequestProductRepo.save(purchaseRequestProduct);
        }


    }


//    public PurchaseRequest createPurchaseRequest(PurchaseRequest purchaseRequest) {
//        // Save the purchase request to the database
//        PurchaseRequest savedPurchaseRequest = purchaseRequestRepo.save(purchaseRequest);
//        return savedPurchaseRequest;
//    }


//    public ResponseEntity<?> createPurchaseRequestt(PurchaseRequest newPurchaseRequest) throws Exception {
//
//
//        Optional<Station> optionalStation = stationRepository.findById(newPurchaseRequest.getSelectedStationID());
//        if (optionalStation.isEmpty()) {
//            throw new Exception("Station not found");
//        }
//        newPurchaseRequest.setStation(optionalStation.get());
//        PurchaseRequestInterface createdPurchaseRequest = purchaseRequestProductRepo.save(newPurchaseRequest);
//        return ResponseEntity.ok(createdPurchaseRequest);
//    }


    @Override
    public void addPurchaseRequestProducttoPurchaseRequest(Long id, PurchaseRequestItemEntity purchaseRequestProduct) {


    }

    public Optional<PurchaseRequestEntity> findById(Long id) {
        return purchaseRequestRepo.findById(id);
    }

    public ResponseEntity<?> createPurchaseRequest(PurchaseRequestEntity newPurchaseRequest) {

        PurchaseRequestEntity createdPurchaseRequest = purchaseRequestRepo.save(newPurchaseRequest);

        return ResponseEntity.ok(new CustomMessage("Purchase Request added."));
    }


//    public boolean updateStatus(Long id) {
//        Optional<PurchaseRequest> purchaseRequest = findById(id);
//        purchaseRequest.setStatus("Completed");
//
//        return saveOrUpdatepurchaseRequest(purchaseRequest);
//    }


//    @Override
//    public void addPurchaseRequestProductToPurchaseRequest(Long id, PurchaseRequest purchaseRequest) {
//      //  invoice.setInvoiceNumber(randomAlphanumeric(8).toUpperCase());
//       PurchaseRequest purchaseRequest =  purchaseRequestRepo.findBypurchaseRequestNumber(purchaseRequest)  ;       //findById(id).get();
//
//
//
//        invoice.setCustomer(customer);
//        invoiceRepository.save(invoice);
//
//   }
//how are we adding products now?
//    @Override
//    public void addPurchaseRequestProductToPurchaseRequest(Long id, PurchaseRequest purchaseRequest, int sequence) {
//        String sequenceNumber = "PR" + String.format("%06d", sequence);
//        purchaseRequest.setPurchaseRequestNumber(sequenceNumber);
//
////        PurchaseRequest purchaseRequest2 = purchaseRequestRepo.findBypurchaseRequestNumber();
//
//        purchaseRequest.setPurchaseRequestNumber(String.valueOf(purchaseRequest));


}


//public PurchaseRequest updatePurchaseRequest(Long purchaseRequest) {
//    PurchaseRequest updatedPurchaseRequest = purchaseRequestRepo.save(purchaseRequest);
//    return updatedPurchaseRequest;
//




