package io.getarrays.securecapita.PurchaseRequest.Controller;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.PurchaseRequest.Service.PurchaseRequestService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/PurchaseRequest")
@RequiredArgsConstructor

public class PurchaseRequestController {
    @Autowired
    PurchaseRequestService purchaseRequestService;

    @PostMapping("/create")
    public PurchaseRequest createPurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
        System.out.println("Incoming purchase request from the client is: " + purchaseRequest);

        PurchaseRequest createdPurchaseRequest = purchaseRequestService.createPurchaseRequest(purchaseRequest);

        System.out.println("Created purchase request is: " + createdPurchaseRequest);

        return createdPurchaseRequest;
    }


    @GetMapping("/getAll")
    public List<PurchaseRequest> getAllPurchaseRequests() {
        List<PurchaseRequest> listOfAllPurchaseRequests = purchaseRequestService.getAllPurchaseRequests();
        return listOfAllPurchaseRequests;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePurchaseRequest(@PathVariable("id") Long purchaseRequestId) {

     purchaseRequestService.deletePurchaseRequest(purchaseRequestId);

        return "purchaseRequest deleted successfully";

    }

//    @PutMapping("/update/{id}")
//    public PurchaseRequest updatePurchaseRequest(@PathVariable("id") Long purchaseRequestId, @RequestBody PurchaseRequest purchaseRequest) {
//        PurchaseRequest oldPurchaseRequest = purchaseRequestService.updatePurchaseRequest(purchaseRequestId);
//;
//
//        PurchaseRequest updatedPurchaseRequest = purchaseRequestService.updatePurchaseRequest(oldPurchaseRequest);
//
//        return updatedPurchaseRequest;
//    }


//    @PutMapping("/update/{id}")
//    public PurchaseRequest updatePurchaseRequest(@PathVariable("id") Long purchaseRequestId, @RequestBody PurchaseRequest purchaseRequest) {
//        PurchaseRequest oldPurchaseRequest = purchaseRequestService.updatePurchaseRequest(purchaseRequestId); // Assuming you have a method to retrieve the old purchase request by ID
//
//        // Update the properties of the old purchase request with the new values
////        oldPurchaseRequest.setProperty1(purchaseRequest.getProperty1());
////        oldPurchaseRequest.setProperty2(purchaseRequest.getProperty2());
//        // Add more properties to update if necessary
//
//        PurchaseRequest updatedPurchaseRequest = purchaseRequestService.updatePurchaseRequest(purchaseRequestId); // Assuming you have a method to update the purchase request
//
//        return updatedPurchaseRequest;
//    }



}


