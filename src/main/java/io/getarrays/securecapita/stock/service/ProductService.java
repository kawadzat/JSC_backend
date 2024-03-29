package io.getarrays.securecapita.stock.service;

import io.getarrays.securecapita.stock.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
//    public Product   saveProduct(Product product);

    public Product   saveProduct(Integer categoryId   ,Product  product);


    public  List<Product> listAllProductByProductStockStatus(String stockStatus);

    public List<Product > listAllProduct  ();
    List<Product[]> getProductTotalQuantities(String productName);


    List<Product> getAllProductsUsingJP(String productName);
    Map<String,Long> calculateProductTotals(String productName);



}
