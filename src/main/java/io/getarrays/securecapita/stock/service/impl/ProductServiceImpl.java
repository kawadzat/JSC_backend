package io.getarrays.securecapita.stock.service.impl;

import io.getarrays.securecapita.stock.model.Category;
import io.getarrays.securecapita.stock.model.Product;
import io.getarrays.securecapita.stock.repository.CategoryRepository;
import io.getarrays.securecapita.stock.repository.ProductRepository;
import io.getarrays.securecapita.stock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepo;



    @Autowired
    private CategoryRepository catRepo;









//    @Override
//    public Product saveProduct(Product product) {
//
//      Product prod =null;
//        Category cat =null;
//
//
//        try {
//            Date date = new Date();
//
////            cat = catRepo.getById(categoryId);
//
//      ;
//            product.setRecievedDate(date);
////            product.setCategory(cat);
////            product.setStockStatus("un-stocked");
//            prod = productRepo.save(product);
//
//        } catch (Exception e) {
//            prod =null;
//        }
//        return prod;
//    }

    @Override
    public Product saveProduct(Integer categoryId, Product product) {

        Product prod =null;

        Category cat =null;
        System.out.print("cat is :"+cat);

        try {
            Date date = new Date();


            cat = catRepo.findByCategoryId(categoryId);


            product.setRecievedDate(date);

            product.setCategory(cat);


           // product.setStockStatus("un-stocked");
            prod = productRepo.save(product);

        } catch (Exception e) {
            prod =null;
        }
        return prod;



   }

    @Override
    public List<Product> listAllProductByProductStockStatus(String stockStatus) {
        return null;
    }

    @Override
    public List<Product> listAllProduct() {
        return null;
    }

    @Override
    public List<Product[]> getProductTotalQuantities(String productName) {
        return null;
    }

    @Override
    public List<Product> getAllProductsUsingJP(String productName) {
        return productRepo.getAllProductsUsingJP(productName);
    }


    public Map<String,Long> calculateProductTotals(String productName) {
        return productRepo.calculateProductTotals(productName);
    }




//
//    @Override
//    public List<Product> listAllProductByProductStockStatus(String stockStatus) {
//        List<Product> listPro =null;
//        try {
//            listPro = productRepo.getAllProductByStockStatus(stockStatus);
//        } catch (Exception e) {
//            listPro =null;
//        }
//        return listPro;
//    }
//
//
//
//    @Override
//    public List<Product> listAllProduct() {
//        List<Product> listPro;
//
//        try {
//            listPro = productRepo.findAll();
//        } catch (Exception e) {
//            listPro =null;
//        }
//        return listPro;
//    }
//
//    @Override
//    public List<Product[]> getProductTotalQuantities(String productName) {
//        return productRepo.calculateTotalQuantityByProductName();
//    }
//
//    @Override
//    public List<Product> getAllProductsUsingJP(String productName) {
//        return   productRepo.getAllProductsUsingJP(productName);
//    }


//    public List<Product> getAllProductsUsingJP(String productName){
//
//        return  productRepo.getAllProductsUsingJP(productName);
//    }




}
