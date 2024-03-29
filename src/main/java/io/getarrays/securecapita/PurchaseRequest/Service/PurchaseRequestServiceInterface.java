package io.getarrays.securecapita.PurchaseRequest.Service;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequestProduct;

public interface PurchaseRequestServiceInterface {


void    addPurchaseRequestProductToPurchaseRequest(Long id, PurchaseRequest purchaseRequestProduct,int sequence);
}
