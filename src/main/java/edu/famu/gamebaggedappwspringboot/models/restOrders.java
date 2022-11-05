package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.v1.FirestoreClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashMap;

import static com.google.cloud.firestore.v1.FirestoreClient.*;
import static com.google.firebase.cloud.FirestoreClient.getFirestore;

@AllArgsConstructor
@NoArgsConstructor
public class restOrders extends abstractOrders {


    public restOrders(HashMap<Double,Double> cashout, String customer, HashMap<Timestamp, Timestamp> deliverywindow, HashMap<GeoPoint,GeoPoint> destinations, String product, String store, bagger Bagger) {
        super(cashout, customer, deliverywindow, destinations, product, store); //calling parent constructor
        this.Bagger = Bagger;
    }


    private bagger Bagger;

    public DocumentReference getBagger(){
        return Bagger ;

    }

    public void setBagger(String Customer, String Bagger){
        //databse connection
        Firestore db = getFirestore();
        //get a reference to collection with a specific category document id
        this.Bagger = db.collection("bagger").document(Bagger);
    }

}
