package io.getarrays.securecapita.stock.service.impl;

import io.getarrays.securecapita.stock.model.Inventory;
import io.getarrays.securecapita.stock.model.Store;
import io.getarrays.securecapita.stock.repository.InventoryRepository;
import io.getarrays.securecapita.stock.repository.StoreRepository;
import io.getarrays.securecapita.stock.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepo;
    @Autowired
    private StoreRepository storeRepo;

    @Override
    public Inventory saveInventory(Integer storeId, Inventory inventory) {

        Store store =null;

        try {
            store = storeRepo.getById(storeId);
            Date date = new Date();
            inventory.setInventoryDate(date);
            inventory.setStore(store);
            inventory = inventoryRepo.save(inventory);

        } catch (Exception e) {
            inventory =null;
        }
        return inventory;
    }
}
