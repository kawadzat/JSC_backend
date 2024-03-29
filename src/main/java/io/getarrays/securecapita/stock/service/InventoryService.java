package io.getarrays.securecapita.stock.service;


import io.getarrays.securecapita.stock.model.Inventory;

import java.util.List;

public interface InventoryService {
    public Inventory saveInventory(Integer storeId, Inventory inventory);
//    public void deleteInventory  ( Integer inventoryId);
//    public Inventory  editInventory  (Integer inventoryId,Integer storeId,Inventory  inventory);
//    public List<Inventory> listAllInventorys  ();
//    public List<Inventory> listAllInventorysByStoreId  (Integer storeId);
//    public Inventory findInventoryByInventoryCode(String inventoryCode);
//    public List<Inventory> findInventoryByReorderLevel(Integer reorderLevel);
//    public List<Inventory> findInventoryByAlertLevel(Integer alertLevel);
//
//
}