package io.getarrays.securecapita.stock.controller;

import io.getarrays.securecapita.stock.model.Inventory;
import io.getarrays.securecapita.stock.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/inventory")
@RequiredArgsConstructor
public class InventoryContorller  {

    @Autowired
    private InventoryService invService;

    @PostMapping("/inventories/{storeId}")
    public ResponseEntity<Inventory> saveInventory(@PathVariable Integer storeId, @RequestBody Inventory inv) {

        Inventory inventory = invService.saveInventory(storeId, inv);

        return ResponseEntity.ok().body(inventory);
    }






}
