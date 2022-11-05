package edu.famu.gamebaggedappwspringboot.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.bagger;
import edu.famu.gamebaggedappwspringboot.models.orders;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service

public class OrderService {

    public ArrayList<bagger> retrieveItemsByBagger(String id) throws ExecutionException, InterruptedException {
        ArrayList<bagger> list = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve multiple documents
        DocumentReference baggerRef = db.collection("bagger").document(id);
        //Allows us to make an async call to the database
        ApiFuture<QuerySnapshot> future = db.collectionGroup("order").whereEqualTo("bagger", baggerRef).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot document : documents)
        {
            bagger Bagger = new bagger();

            //ApiFuture allows us to make async calls to the database
            ApiFuture<DocumentSnapshot> baggerFuture = baggerRef.get();
            //Retrieve document
            DocumentSnapshot baggerDoc = baggerFuture.get();

            if(baggerDoc.exists())
                Bagger = baggerDoc.toObject(bagger.class);
            //add the bagger to the list
            list.add(new orders(document.getId(), (HashMap<Double,Double>) document.get("cashout"),document.getString("customer"),(HashMap< Timestamp, Timestamp>) document.get("deliverywindow"),  (HashMap<GeoPoint,GeoPoint>) document.get("destinations"),document.getString("product"), document.getString("store"), Bagger));


        }

        return list;
    }
}
