package io.getarrays.securecapita.PurchaseRequest.Controller;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequestProduct;
import io.getarrays.securecapita.PurchaseRequest.Service.PurchaseRequestService;

import io.getarrays.securecapita.PurchaseRequest.Service.PurchaseRequestServiceReport;
import io.getarrays.securecapita.domain.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/PurchaseRequest")
@RequiredArgsConstructor

public class PurchaseRequestController {
    @Autowired
    PurchaseRequestService purchaseRequestService;

    PurchaseRequestServiceReport purchaseRequestServiceReport;

    @GetMapping("/generate-report")
    public ResponseEntity<Resource> generateReport() {
        // Generate the report as shown in the previous step
        File file = new File("path/to/output/report.pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new FileSystemResource(file));
    }

//    @GetMapping("/purchaseRequest/{purchaseRequestId}")
//    public ResponseEntity<PurchaseRequest> getUserById(@PathVariable Long purchaseRequestId) {
//        PurchaseRequest purchaseRequest = purchaseRequestService.getPurchaseRequestById();
//
//        if (purchaseRequest != null) {
//            return ResponseEntity.ok(purchaseRequest);
//        }
//
//        return ResponseEntity.notFound().build();
//    }


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

//    @PostMapping("/addtoassert/{id}")
//    public ResponseEntity<HttpResponse> addPurchaseRequestProducctToPurchaseRequests(@PathVariable("id") Long id, @RequestBody PurchaseRequestProduct purchaseRequestProduct) {
//        purchaseRequestService.addProductsToPurchaseRequest(id, purchaseRequestProduct);
////    UserDTO userDTO = userService.getUserByEmail(user.getEmail());
//        List<PurchaseRequest> asserts = (List<PurchaseRequest>) purchaseRequestService.getPurchaseRequestById();
//
//        HttpResponse response = HttpResponse.builder()
//                .timeStamp(LocalDateTime.now().toString())
////            .data(Map.of("user", userDTO, "assert", asserts))
//                .message(String.format("PurchaseRequestProduct added to PurchaseRequests with ID: %s", id))
//                .status(OK)
//                .statusCode(OK.value())
//                .build();
//
//        return ResponseEntity.ok(response);
//    }

//    @PutMapping("/update/{id}")
//    public PurchaseRequest updatePurchaseRequest(@PathVariable("id") Long purchaseRequestId, @RequestBody PurchaseRequest purchaseRequest) {
//        PurchaseRequest oldPurchaseRequest = purchaseRequestService.updatePurchaseRequest(purchaseRequestId);
//;
//
//        PurchaseRequest updatedPurchaseRequest = purchaseRequestService.updatePurchaseRequest(oldPurchaseRequest);
//
//        return updatedPurchaseRequest;
//    }

    @RequestMapping("get")
    @ResponseBody
    public Optional<PurchaseRequest> findById(long id)
    {
        return purchaseRequestService.findById(id);
    }

    @GetMapping("/get/{purchaseRequestId}")
    public PurchaseRequest getPurchaseRequestById(@PathVariable Long purchaseRequestId) {
        return purchaseRequestService.getPurchaseRequestById(purchaseRequestId);
    }



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


