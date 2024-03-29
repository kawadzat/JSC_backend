package io.getarrays.securecapita.PurchaseRequest.Service;


import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequestProduct;
import io.getarrays.securecapita.PurchaseRequest.Repository.PurchaseRequestProductRepo;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class PurchaseRequestProductService {

    @Autowired
    PurchaseRequestProductRepo purchaseRequestProductRepo;

    public PurchaseRequestProductService(PurchaseRequestProductRepo purchaseRequestProductRepo) {
        this.purchaseRequestProductRepo = purchaseRequestProductRepo;
    }

    public PurchaseRequestProduct createPurchaseRequestProduct(PurchaseRequestProduct   purchaseRequestProduct) {

        PurchaseRequestProduct createdPurchaseRequestProduct = purchaseRequestProductRepo.save(purchaseRequestProduct);

        return createdPurchaseRequestProduct;
    }

    public List<PurchaseRequestProduct> getAllPurchaseRequestsProducts() {
        List<PurchaseRequestProduct> allPurchaseRequests = purchaseRequestProductRepo.findAll();
        return allPurchaseRequests;
    }








    public void deletePurchaseRequestProduct(Long purchaseRequestProductId) {
        purchaseRequestProductRepo.deleteById(purchaseRequestProductId);
    }


}
