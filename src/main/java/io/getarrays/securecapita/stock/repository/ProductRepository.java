package io.getarrays.securecapita.stock.repository;

import io.getarrays.securecapita.stock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {
//    @Query("SELECT p FROM Product p  JOIN  p.order o  WHERE o.orderId =:orderId")
//    List<Product> getAllProductByOrderId(@Param(value ="orderId") Integer orderId);
//    @Query("SELECT p FROM Product p  JOIN  p.category c  WHERE c.categoryId =:categoryId")
//    List<Product> getAllProductByCategory(@Param (value = "categoryId") Integer brandId);
//    @Query("SELECT p FROM Product p  JOIN  p.brand b  WHERE b.brandId=:brandId")
//    List<Product> getAllProductByBrand(@Param (value = "brandId") Integer brandId);
//    @Query("SELECT p FROM Product p  JOIN  p.inventory inv  WHERE inv.inventoryId=:inventoryId")
//    List<Product> getAllProductByInventory(@Param (value = "inventoryId") Integer inventoryId);
//    @Query("SELECT p FROM Product p   WHERE p.productNumber=:productNumber ")
//    Product getProductbyProductNumner(@Param (value = "productNumber")String productNumber);
//@Query("SELECT p.productName, SUM(p.productQuantity) AS totalQuantity FROM Product p GROUP BY p.productName")
//List<Product[]> calculateTotalQuantityByProductName();
//
//
//    @Query("SELECT p FROM Product p    WHERE p.stockStatus=:stockStatus")
//    List<Product> getAllProductByStockStatus(@Param (value = "stockStatus") String stockStatus);
//
//
//    @Query(value = "SELECT p.productName, SUM(p.quantity) as sum from product p where p.productName=:n")
//    Map<String,Double> calculateProductTotals(@Param("n") String productName);


    @Query(value = "SELECT p.productName, SUM(p.productQuantity) AS totalQuantity FROM Product p WHERE p.productName = :n GROUP BY p.productName")
    Map<String, Long> calculateProductTotals(@Param("n") String productName);

    @Query("select p from Product p where p.productName=:n" )
    List<Product>getAllProductsUsingJP(@Param("n")String productName);





}
