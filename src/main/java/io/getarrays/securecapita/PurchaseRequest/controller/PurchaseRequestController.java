package io.getarrays.securecapita.PurchaseRequest.controller;

import io.getarrays.securecapita.PurchaseRequest.service.PurchaseRequestService;

import io.getarrays.securecapita.PurchaseRequest.service.PurchaseRequestServiceReport;
import io.getarrays.securecapita.PurchaseRequest.entity.PurchaseRequestEntity;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.roles.prerunner.ROLE_AUTH;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

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


//    @PostMapping("/create")
//    public PurchaseRequest createPurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
//        System.out.println("Incoming purchase request from the client is: " + purchaseRequest);
//
//        PurchaseRequest createdPurchaseRequest = purchaseRequestService.createPurchaseRequest(purchaseRequest);
//
//        System.out.println("Created purchase request is: " + createdPurchaseRequest);
//
//        return createdPurchaseRequest;
//    }






    @PostMapping("/create")
    public ResponseEntity<?> createPurchaseRequest (@RequestBody @Validated PurchaseRequestEntity newPurchaseRequest ) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.CREATE_PURCHASEREQUEST.name()))) {
            return  purchaseRequestService.createPurchaseRequest(newPurchaseRequest);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));

    }





//    @PostMapping("/create")
//    public ResponseEntity<?> createAssert(@RequestBody @Validated AssertEntity newAssert) throws Exception {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.CREATE_ASSET.name()))) {
//            return assertService.createAssert(newAssert);
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));
//
//    }






    @GetMapping("/getAll")
    public List<PurchaseRequestEntity> getAllPurchaseRequests() {
        List<PurchaseRequestEntity> listOfAllPurchaseRequests = purchaseRequestService.getAllPurchaseRequests();
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
    public Optional<PurchaseRequestEntity> findById(long id)
    {
        return purchaseRequestService.findById(id);
    }

    @GetMapping("/get/{purchaseRequestId}")
    public PurchaseRequestEntity getPurchaseRequestById(@PathVariable Long purchaseRequestId) {
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


