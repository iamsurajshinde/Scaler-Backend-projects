package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.*;
import org.example.evaluations.evaluation.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<String> getNamesOfAllCategoriesAndLinkedProductsAndTheirImages() {
        // Use single query with JOIN FETCH to minimize queries (N+1 problem avoided)
        List<Category> categories = categoryRepo.findAllWithProductsAndImages();

        List<String> result = new ArrayList<>();

        for (Category c : categories) {
            String categoryName = c.getTitle();
            if (c.getProducts() != null) {
                for (Product p : c.getProducts()) {
                    String productName = p.getName();
                    if (p.getImages() != null) {
                        for (Image img : p.getImages()) {
                            result.add("Category: " + categoryName + 
                                       ", Product: " + productName + 
                                       ", Image: " + img.getDescriptiveName());
                        }
                    } else {
                        result.add("Category: " + categoryName + ", Product: " + productName);
                    }
                }
            } else {
                result.add("Category: " + categoryName);
            }
        }
        return result;
    }


    public List<String> getNamesOfAllCategoriesAndTheirSubCategories() {
        List<String> result = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 3); // batch size = 3
        Page<Category> page;
        List<Category> categories;
        do {
            categories = categoryRepo.findAllWithSubCategories(pageable);
            for (Category c : categories) {
                String categoryName = c.getTitle();
                if (c.getSubCategories() != null) {
                    for (SubCategory sc : c.getSubCategories()) {
                        result.add("Category: " + categoryName + ", SubCategory: " + sc.getName());
                    }
                } else {
                    result.add("Category: " + categoryName);
                }
            }
            pageable = pageable.next();
        } while (!categories.isEmpty());

        return result;
    }

}
