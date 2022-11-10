package edu.famu.gamebaggedappwspringboot.controllers;

import edu.famu.gamebaggedappwspringboot.models.Category;
import edu.famu.gamebaggedappwspringboot.models.Product;
import edu.famu.gamebaggedappwspringboot.models.RestProduct;
import edu.famu.gamebaggedappwspringboot.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

//first mapping line on pages
@RequestMapping("/api/product")
@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    //Get product items by category id
    @GetMapping("/category/{id}")
    public ArrayList<Product> getProductItemsByCategory(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return productService.retrieveProductByCategory(id);
    }

    //Get all categories
    @GetMapping("/")
    public ArrayList<Product> getProduct() throws ExecutionException, InterruptedException {
        return productService.getProduct();
    }
}
