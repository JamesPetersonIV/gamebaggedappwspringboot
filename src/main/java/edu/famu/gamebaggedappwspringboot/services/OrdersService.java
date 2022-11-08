package edu.famu.gamebaggedappwspringboot.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.Orders;
import edu.famu.gamebaggedappwspringboot.models.Product;
import edu.famu.gamebaggedappwspringboot.models.Users;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrdersService {

    public ArrayList<Orders> retrieveItemsByOrders(String id) throws ExecutionException, InterruptedException {
        ArrayList<Orders> list = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve multiple documents
        DocumentReference prodRef = db.collection("product").document(id);

        DocumentReference cusRef = db.collection("users").document(id);

        DocumentReference bagRef = db.collection("users").document(id);

        //Allows us to make an async call to the database
        ApiFuture<QuerySnapshot> future = db.collectionGroup("orders").whereEqualTo("bagger", bagRef).get();

        future = db.collectionGroup("orders").whereEqualTo("customer", cusRef).get();

        future = db.collectionGroup("orders").whereEqualTo("product", prodRef).get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot document : documents)
        {
            Users bagger = new Users();

            Users customer = new Users();

            Product product = new Product();

            //ApiFuture allows us to make async calls to the database
            ApiFuture<DocumentSnapshot> bagFuture = bagRef.get();
            //Retrieve document
            DocumentSnapshot bagDoc = bagFuture.get();

            ApiFuture<DocumentSnapshot> cusFuture = cusRef.get();
            DocumentSnapshot cusDoc = cusFuture.get();

            ApiFuture<DocumentSnapshot> prodFuture = prodRef.get();
            DocumentSnapshot prodDoc = prodFuture.get();

            if(bagDoc.exists())
                bagger = bagDoc.toObject(Users.class);

            if(cusDoc.exists())
                customer = cusDoc.toObject(Users.class);

            if(prodDoc.exists())
                product = prodDoc.toObject(Product.class);

            //add the bagger, customer, and product to the list
            list.add(new Orders(document.getId(), (HashMap<Double,Double>) document.get("cashout"), (HashMap < Timestamp, Timestamp>) document.get("deliverywindow"), (HashMap < GeoPoint, GeoPoint>) document.get("destinations"), document.getString("store"), bagger, customer, product));

        }

        return list;
    }
}
