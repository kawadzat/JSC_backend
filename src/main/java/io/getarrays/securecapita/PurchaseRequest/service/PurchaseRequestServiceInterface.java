package io.getarrays.securecapita.PurchaseRequest.service;

import io.getarrays.securecapita.PurchaseRequest.entity.PurchaseRequestEntity;

public interface PurchaseRequestServiceInterface {


void    addPurchaseRequestProductToPurchaseRequest(Long id, PurchaseRequestEntity purchaseRequestProduct, int sequence);
}
