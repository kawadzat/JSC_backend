package io.getarrays.securecapita.stock.repository;

import io.getarrays.securecapita.stock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByCategoryId(Integer categoryId);
    public Category findBycategoryName( String categoryName);

//    @Modifying
//    @Query("Update Category c set c.categoryName= :categoryName, c.categoryDescription = :categoryDescription"
//            + "  WHERE c.categoryId = :categoryId")
//    public Integer editCategory( @Param("categoryId") Integer categoryId,
//                                 @Param("categoryName") String categoryName,
//                                 @Param("categoryDescription") String categoryDescription);
}
