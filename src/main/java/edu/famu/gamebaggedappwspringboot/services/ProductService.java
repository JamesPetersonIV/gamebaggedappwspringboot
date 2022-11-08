package edu.famu.gamebaggedappwspringboot.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.Category;
import edu.famu.gamebaggedappwspringboot.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service

public class ProductService {

    public ArrayList<Product> retrieveProductByCategory(String id) throws ExecutionException, InterruptedException {
        ArrayList<Product> list = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve multiple documents
        DocumentReference catRef = db.collection("category").document(id);
        //Allows us to make an async call to the database
        ApiFuture<QuerySnapshot> future = db.collectionGroup("Product").whereEqualTo("category", catRef).get();
        // future.get blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot document : documents)
        {
            Category category = new Category();

            //ApiFuture allows us to make async calls
            ApiFuture<DocumentSnapshot> catFuture = catRef.get();
            //Retrieve document
            DocumentSnapshot catDoc = catFuture.get();

            if(catDoc.exists())
                category = catDoc.toObject(Category.class);
            //add the category to list
            list.add(new Product(document.getId(), document.getString("name"), document.getString("description"), document.getDouble("price"), category));

        }

        return list;
    }
}
