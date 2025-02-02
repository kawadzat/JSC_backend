package io.getarrays.securecapita.PurchaseRequest.service;

import io.getarrays.securecapita.PurchaseRequest.entity.PurchaseRequestItemEntity;

public interface PurchaseRequestInterface  {
    void addProductsToPurchaseRequest(Long id,  PurchaseRequestItemEntity purchaseRequestProduct);


    void addPurchaseRequestProducttoPurchaseRequest(Long id, PurchaseRequestItemEntity purchaseRequestProduct);


}
