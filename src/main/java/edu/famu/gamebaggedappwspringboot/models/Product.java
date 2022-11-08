package edu.famu.gamebaggedappwspringboot.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Product extends AbstractProduct {

    private Category category;

    public Product(String id, String name, String description, Double price, Category category) {
        super(id, name, description, price);
        this.category = category;
    }

}
