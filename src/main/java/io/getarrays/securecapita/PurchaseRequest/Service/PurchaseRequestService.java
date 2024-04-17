package io.getarrays.securecapita.PurchaseRequest.Service;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequestProduct;
import io.getarrays.securecapita.PurchaseRequest.Repository.PurchaseRequestProductRepo;
import io.getarrays.securecapita.PurchaseRequest.Repository.PurchaseRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PurchaseRequestService   implements PurchaseRequestInterface {
    @Autowired
    public PurchaseRequestProductRepo purchaseRequestProductRepo;
    @Autowired
    PurchaseRequestRepo purchaseRequestRepo;


    public PurchaseRequest getPurchaseRequestById(Long purchaseRequestId) {


        Optional<PurchaseRequest> optionalpurchaseRequest = purchaseRequestRepo.findById(purchaseRequestId);

        boolean isPresent = optionalpurchaseRequest.isPresent();

        if(isPresent) {

            PurchaseRequest purchaseRequest =optionalpurchaseRequest.get();
            return purchaseRequest;
        }

        return null;

    }




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
    /* get specific user based on Id */




    public void deletePurchaseRequest(Long purchaseRequestId) {
   purchaseRequestRepo.deleteById(purchaseRequestId);
}

    @Override
    public void addProductsToPurchaseRequest(Long id, PurchaseRequestProduct purchaseRequestProduct) {


                 Optional <PurchaseRequest>   purchaseRequestOptional =  purchaseRequestRepo.findById(id);

        if (purchaseRequestOptional.isPresent()) {
            PurchaseRequest purchaseRequest = purchaseRequestOptional.get();
            purchaseRequestProduct.setPurchaseRequest(purchaseRequest);
            purchaseRequestProductRepo.save(purchaseRequestProduct);
        }


    }

    @Override
    public void addPurchaseRequestProducttoPurchaseRequest(Long id, PurchaseRequestProduct purchaseRequestProduct) {

        



    }

    public Optional<PurchaseRequest> findById(Long id) {
        return purchaseRequestRepo.findById(id);
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




