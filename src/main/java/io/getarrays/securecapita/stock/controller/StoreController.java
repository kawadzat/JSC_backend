package io.getarrays.securecapita.stock.controller;

import io.getarrays.securecapita.stock.model.Store;
import io.getarrays.securecapita.stock.service.StoreService;
import io.getarrays.securecapita.stock.service.StoreService1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/store")
@RequiredArgsConstructor
public class StoreController {

    @Autowired
    private StoreService1 storeService1;

    @Autowired
    private StoreService storeService;



//    @PostMapping("/create")
//    public Store   createStore (@RequestBody Store  store) {
//
//        Store createdStore = storeService1.createStore (store);
//        return  createdStore   ;
//    }



    @PostMapping("/create")
    public ResponseEntity<Store> saveStore(@RequestBody Store storeData){
        Store store=  storeService.saveStore( storeData);

        return  ResponseEntity.ok(store);
    }




}
