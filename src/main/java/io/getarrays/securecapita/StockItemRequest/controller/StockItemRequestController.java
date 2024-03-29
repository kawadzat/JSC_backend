package io.getarrays.securecapita.StockItemRequest.controller;


import io.getarrays.securecapita.StockItemRequest.domain.StockItemRequest;
import io.getarrays.securecapita.StockItemRequest.service.StockItemRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/StockItemRequest")
@RequiredArgsConstructor

public class StockItemRequestController {

    @Autowired
    StockItemRequestService stockItemRequestService;

    @PostMapping("/create")
    public StockItemRequest createStockItemRequest(@RequestBody StockItemRequest stockItemRequest) {
        System.out.println("Incoming stock item request from the client is: " + stockItemRequest);

        StockItemRequest createdStockItemRequest = stockItemRequestService.createStockItemRequest(stockItemRequest);

        System.out.println("Created stock item request is: " + createdStockItemRequest);

        return createdStockItemRequest;
    }




    @GetMapping("/get")
    public List<StockItemRequest> getAllStockItemRequests() {
        List<StockItemRequest> listOfAllStockItemRequests = stockItemRequestService.getAllStockItemRequests();
        return listOfAllStockItemRequests;
    }

    @DeleteMapping("/delete/{Id}")
    public String deleteStockItemRequest(@PathVariable("id") Long stockItemRequestId) {

       stockItemRequestService.deleteStockItemRequest(stockItemRequestId);

        return "Stock item request deleted successfully";
    }

//    @PutMapping("/update/{Id}")
//    public StockItemRequest updateStockItemRequest(@PathVariable("id") Long stockItemRequestId, @RequestBody StockItemRequest stockItemRequest) {
//        StockItemRequest oldStockItemRequest = stockItemRequestService.updateStockItemRequest(stockItemRequestId);
//
//        // Update relevant fields from the received request
//        oldStockItemRequest.setQuantity(stockItemRequest.getQuantity()); // Example: Update quantity field
//        // Update other relevant fields based on your StockItemRequest class
//
//        StockItemRequest updatedStockItemRequest = stockItemRequestService.updateStockItemRequest(oldStockItemRequest.getId());
//
//        return updatedStockItemRequest;
//    }


}
