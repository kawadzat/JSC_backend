package io.getarrays.securecapita.stock.controller;

import io.getarrays.securecapita.stock.model.Product;
import io.getarrays.securecapita.stock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor

public class ProductController {
    @Autowired
    private ProductService prodService;


//    @PostMapping("/create")
//    public ResponseEntity<Product> saveProduct(   @RequestBody Product ProData){
//        Product product = prodService.saveProduct(ProData);
//        return  ResponseEntity.ok().body(product);
//    }


    @PostMapping("/create/{categoryId}")
    public ResponseEntity<Product> saveProduct(
            @PathVariable Integer categoryId, @RequestBody Product ProData){
        Product product = prodService.saveProduct( categoryId, ProData);
        return  ResponseEntity.ok().body(product);
    }

//    @GetMapping("/product/{name}/quantity")
//    public List<Product[]> getProductQuantityByName(@PathVariable String productName) {
//        return prodService.getProductTotalQuantities(productName);
//    }

    @GetMapping("/product/{productName}/quantity")
    public List<Product[]> getProductQuantityByName(@PathVariable("productName") String productName) {
        return prodService.getProductTotalQuantities(productName);
    }



    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> listPro = prodService.listAllProduct();
        return ResponseEntity.ok().body(listPro);
    }



    @GetMapping("/products/un-stocked")
    public ResponseEntity<List<Product>> getAllUnStockedProducts(){

        List<Product> listPro = prodService.listAllProductByProductStockStatus("un-stocked");
        return ResponseEntity.ok().body(listPro);
    }
    @GetMapping("/products/stocked")
    public ResponseEntity<List<Product>> getAllStockedProducts(){

        List<Product> listPro = prodService.listAllProductByProductStockStatus("stocked");
        return ResponseEntity.ok().body(listPro);
    }


    @GetMapping("/j/{productName}")
    public List<Product> jpqlFindAllJPQL(@PathVariable String productName)
    {
        return  prodService.getAllProductsUsingJP(productName);
    }



    @GetMapping("/g/{productName}")
    public Object jpqlFindAllJPQLSum(@PathVariable String productName)
    {
        Object fetchResponse = prodService.calculateProductTotals(productName);

        // TODO: Map<String, Long>

        return  fetchResponse;
    }









}
