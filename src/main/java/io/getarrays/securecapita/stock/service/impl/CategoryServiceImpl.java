package io.getarrays.securecapita.stock.service.impl;


import io.getarrays.securecapita.stock.model.Category;
import io.getarrays.securecapita.stock.repository.CategoryRepository;
import io.getarrays.securecapita.stock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository catRepo;


    @Override
    public Category AddCategory(Category cat) {
        Category savedCat  =null;
        try {
            savedCat = catRepo.save(cat);
        } catch (Exception e) {
            savedCat =null;
        }

        return savedCat;
    }





}
