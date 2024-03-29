package io.getarrays.securecapita.stock.controller;

import io.getarrays.securecapita.stock.model.Category;
import io.getarrays.securecapita.stock.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService catService;


    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category cat){
        Category savedCat = catService.AddCategory(cat);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "CategoryController");
        return ResponseEntity.accepted().headers(headers).body(savedCat);
    }


}
