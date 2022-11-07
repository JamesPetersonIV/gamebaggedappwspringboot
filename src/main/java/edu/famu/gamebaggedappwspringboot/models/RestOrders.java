package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.firebase.cloud.FirestoreClient;

import java.sql.Timestamp;
import java.util.HashMap;

public class RestOrders extends AbstractOrders {
    public RestOrders(HashMap<Double,Double> cashout, String customer, HashMap <Timestamp, Timestamp> deliverywindow, HashMap <GeoPoint, GeoPoint> destinations, String product, String store, Bagger bagger) {
        super(cashout, customer, deliverywindow, destinations, product, store); //calling parent constructor
        this.bagger = bagger;
    }

    private DocumentReference bagger;

    public DocumentReference getBagger() {
        return bagger;
    }

    public void setBagger(String bagger){
        //database connection
        Firestore db = FirestoreClient.getFirestore();
        //get a reference to the collection with a specific category document id
        this.bagger = db.collection("bagger").document(bagger);
    }
}
