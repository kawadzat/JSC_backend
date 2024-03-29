package io.getarrays.securecapita.StockItemRequest.service;


import io.getarrays.securecapita.StockItemRequest.domain.StockItemRequest;
import io.getarrays.securecapita.StockItemRequest.repository.StockItemRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockItemRequestService {

    @Autowired
    StockItemRequestRepository stockItemRequestRepository;

    public StockItemRequestService(StockItemRequestRepository stockItemRequestRepository) {
        this.stockItemRequestRepository = stockItemRequestRepository;
    }

    public StockItemRequest createStockItemRequest(StockItemRequest stockItemRequest) {

        StockItemRequest createdStockItemRequest = stockItemRequestRepository.save(stockItemRequest);

        return createdStockItemRequest;
    }

    public List<StockItemRequest> getAllStockItemRequests() {
        List<StockItemRequest> allStockItemRequests = stockItemRequestRepository.findAll();
        return allStockItemRequests;
    }

    public void deleteStockItemRequest(long stockItemRequestId) {
        stockItemRequestRepository.deleteById(stockItemRequestId);
    }

//    public StockItemRequest updateStockItemRequest(Long stockItemRequest) {
//        StockItemRequest updatedStockItemRequest =stockItemRequestRepository .save(stockItemRequest);
//        return updatedStockItemRequest;
//
//
//    }
}