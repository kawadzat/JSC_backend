package io.getarrays.securecapita.stock.service.impl;

import io.getarrays.securecapita.stock.model.Store;
import io.getarrays.securecapita.stock.repository.StoreRepository;
import io.getarrays.securecapita.stock.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepo;

    @Override
    public Store saveStore(Store storeData) {

        try {


//

//                comp.getStore().add(storeData);
            storeData= storeRepo.save(storeData);
        } catch (Exception e) {
        }
        return  storeData;
    }
}
