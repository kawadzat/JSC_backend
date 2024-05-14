package io.getarrays.securecapita.PurchaseRequest.Service;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequestProduct;
import io.getarrays.securecapita.asserts.model.Inspection;

public interface PurchaseRequestInterface  {
    void addProductsToPurchaseRequest(Long id,  PurchaseRequestProduct purchaseRequestProduct);


    void addPurchaseRequestProducttoPurchaseRequest(Long id, PurchaseRequestProduct purchaseRequestProduct);


}
