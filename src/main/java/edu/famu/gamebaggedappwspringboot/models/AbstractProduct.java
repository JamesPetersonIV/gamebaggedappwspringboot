package edu.famu.gamebaggedappwspringboot.models;


import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class AbstractProduct {
    @DocumentId
    protected String id;
    protected String name;
    protected String description;
    protected Double price;
}
