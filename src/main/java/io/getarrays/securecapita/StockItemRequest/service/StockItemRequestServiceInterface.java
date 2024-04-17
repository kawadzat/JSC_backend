package io.getarrays.securecapita.StockItemRequest.service;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequestProduct;
import io.getarrays.securecapita.StockItemRequest.domain.StockItemRequestProduct;
import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;

import java.util.Optional;

public interface StockItemRequestServiceInterface {

  void addStockItemRequestProducttoStockItemRequest    (Long id, StockItemRequestProduct stockItemRequestProduct);












}
