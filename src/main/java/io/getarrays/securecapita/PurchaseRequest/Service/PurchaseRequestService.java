package io.getarrays.securecapita.PurchaseRequest.Service;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.PurchaseRequest.Repository.PurchaseRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PurchaseRequestService   implements PurchaseRequestServiceInterface {

    @Autowired
    PurchaseRequestRepo purchaseRequestRepo;

    public PurchaseRequestService(PurchaseRequestRepo purchaseRequestRepo) {
        this.purchaseRequestRepo = purchaseRequestRepo;

}

public PurchaseRequest createPurchaseRequest(PurchaseRequest purchaseRequest) {

    PurchaseRequest createdPurchaseRequest = purchaseRequestRepo.save(purchaseRequest);

    return createdPurchaseRequest;
}


public List<PurchaseRequest> getAllPurchaseRequests() {
    List<PurchaseRequest> allPurchaseRequests = purchaseRequestRepo.findAll();
    return allPurchaseRequests;
}



public void deletePurchaseRequest(Long purchaseRequestId) {
   purchaseRequestRepo.deleteById(purchaseRequestId);
}



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

    @Override
    public void addPurchaseRequestProductToPurchaseRequest(Long id, PurchaseRequest purchaseRequest, int sequence) {
        String sequenceNumber = "PR" + String.format("%06d", sequence);
        purchaseRequest.setPurchaseRequestNumber(sequenceNumber);

//        PurchaseRequest purchaseRequest2 = purchaseRequestRepo.findBypurchaseRequestNumber();

        purchaseRequest.setPurchaseRequestNumber(String.valueOf(purchaseRequest));





    }


//public PurchaseRequest updatePurchaseRequest(Long purchaseRequest) {
//    PurchaseRequest updatedPurchaseRequest = purchaseRequestRepo.save(purchaseRequest);
//    return updatedPurchaseRequest;
//}


}


