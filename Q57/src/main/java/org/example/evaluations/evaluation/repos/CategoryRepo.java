package org.example.evaluations.evaluation.repos;

import java.util.List;

import org.example.evaluations.evaluation.models.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>  {
    @Query("SELECT DISTINCT c FROM Category c " +
           "LEFT JOIN FETCH c.products p " +
           "LEFT JOIN FETCH p.images")
    List<Category> findAllWithProductsAndImages();

    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.subCategories")
    List<Category> findAllWithSubCategories(Pageable pageable);
}
