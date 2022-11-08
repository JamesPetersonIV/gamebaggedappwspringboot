package edu.famu.gamebaggedappwspringboot.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service

public class CategoryService {

    public ArrayList<Category> getCategories() throws ExecutionException, InterruptedException {
        ArrayList<Category> categories = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection("category").get();
        //future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot document : documents)
        {
            categories.add(document.toObject(Category.class));
        }

        return categories;
    }

    public Category getCategory(String name) throws ExecutionException, InterruptedException {
        Category category = new Category();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //async retrieve document
        DocumentReference docRef = db.collection("category").document(name);
        //async retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();

        //
        DocumentSnapshot document = future.get();

        if (document.exists())
            category = document.toObject(Category.class);

        return category;
    }
}
