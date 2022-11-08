package edu.famu.gamebaggedappwspringboot.controllers;

import edu.famu.gamebaggedappwspringboot.models.Category;
import edu.famu.gamebaggedappwspringboot.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/category")
@RestController

public class CategoryController {

    //service variable
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //Get all categories
    @GetMapping("/")
    public ArrayList<Category> getCategories() throws ExecutionException, InterruptedException {
        return categoryService.getCategories();
    }

    //Get a category by it's document id
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable(name = "id") String id) throws ExecutionException, InterruptedException {
        return categoryService.getCategory(id);
    }
}
