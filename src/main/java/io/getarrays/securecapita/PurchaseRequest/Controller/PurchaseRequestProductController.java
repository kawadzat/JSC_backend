package io.getarrays.securecapita.PurchaseRequest.Controller;


import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequestProduct;
import io.getarrays.securecapita.PurchaseRequest.Service.PurchaseRequestProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/PurchaseRequestProduct")
@RequiredArgsConstructor


public class PurchaseRequestProductController {

    @Autowired
    PurchaseRequestProductService purchaseRequestProductService;

    @PostMapping("/create")
    public PurchaseRequestProduct createPurchaseRequestProduct(@RequestBody PurchaseRequestProduct purchaseRequestProduct) {
        System.out.println("Incoming purchase request of product from the client is: " + purchaseRequestProduct);

        PurchaseRequestProduct createdPurchaseRequestProduct = purchaseRequestProductService.createPurchaseRequestProduct(purchaseRequestProduct);

        System.out.println("Created purchase request product is: " + createdPurchaseRequestProduct);

        return createdPurchaseRequestProduct;
    }




 @GetMapping("/getAll")
    public List<PurchaseRequestProduct> getAllPurchaseRequestProducts() {
        List<PurchaseRequestProduct> listOfAllPurchaseRequestProducts = purchaseRequestProductService.getAllPurchaseRequestsProducts();
        return  listOfAllPurchaseRequestProducts  ;
    }


    @DeleteMapping("/delete/{id}")
    public String deletePurchaseRequestProduct(@PathVariable("id") Long purchaseRequestProductId) {

        purchaseRequestProductService.deletePurchaseRequestProduct(purchaseRequestProductId);

        return "purchaseRequestProduct deleted successfully";
}
}
