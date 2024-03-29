package io.getarrays.securecapita.stock.service;

import io.getarrays.securecapita.stock.model.Store;
import io.getarrays.securecapita.stock.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService1 {
    @Autowired
    private final StoreRepository storeRepository;


    public StoreService1(StoreRepository storeRepository) {

        this.storeRepository = storeRepository;
    }

    public Store createStore(Store store) {

        Store createdStore =storeRepository.save(store);

        return createdStore;
    }



}
