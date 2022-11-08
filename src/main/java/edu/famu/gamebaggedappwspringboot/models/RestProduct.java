package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import javax.print.Doc;

public class RestProduct extends AbstractProduct {

    public RestProduct(String id, String name, String description, Double price, DocumentReference category) {
        super(id, name, description, price);
        this.category = category;
    }

    private DocumentReference category;

    public DocumentReference getCategory() {
        return category;
    }

    public void setCategory (String category) {
        //
        Firestore db = FirestoreClient.getFirestore();
        //
        this.category = db.collection("category").document(category);
    }


}
