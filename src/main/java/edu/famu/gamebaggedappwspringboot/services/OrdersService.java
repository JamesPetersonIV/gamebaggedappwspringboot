package edu.famu.gamebaggedappwspringboot.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.Bagger;
import edu.famu.gamebaggedappwspringboot.models.Orders;
import org.springframework.stereotype.Service;

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
        DocumentReference bagRef = db.collection("bagger").document(id);
        //Allows us to make an async call to the database
        ApiFuture<QuerySnapshot> future = db.collectionGroup("orders").whereEqualTo("bagger", bagRef).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot document : documents)
        {
            Bagger bagger = new Bagger();

            //ApiFuture allows us to make async calls to the database
            ApiFuture<DocumentSnapshot> bagFuture = bagRef.get();
            //Retrieve document
            DocumentSnapshot bagDoc = bagFuture.get();

            if(bagDoc.exists())
                bagger = bagDoc.toObject(Bagger.class);
            //add the category to the list
            list.add(new Orders(document.getId(), (HashMap<Double,Double>) document.get("cashout"), document.getString("customer"),(HashMap < Timestamp, Timestamp>) document.get("deliverywindow"), (HashMap < GeoPoint, GeoPoint>) document.get("destinations"), document.getString("product"), document.getString("store"), bagger));

        }

        return list;
    }
}
