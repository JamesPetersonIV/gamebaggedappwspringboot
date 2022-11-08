package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.firebase.cloud.FirestoreClient;

import java.sql.Timestamp;
import java.util.HashMap;

public class RestOrders extends AbstractOrders {
    private DocumentReference bagger;
    private  DocumentReference customer;
    private DocumentReference product;

    public RestOrders(String id, HashMap<Double,Double> cashout, HashMap <Timestamp, Timestamp> deliverywindow, HashMap <GeoPoint, GeoPoint> destinations, String store, DocumentReference bagger, DocumentReference customer, DocumentReference product) {
        super(id, cashout, deliverywindow, destinations, store); //calling parent constructor
        this.bagger = bagger;
        this.customer = customer;
        this.product = product;
    }



    public DocumentReference getBagger() {
        return bagger;
    }
    public DocumentReference getCustomer() {
        return customer;
    }
    public DocumentReference getProduct() {
        return product;
    }


    public void setBagger(String bagger){
        //database connection
        Firestore db = FirestoreClient.getFirestore();
        //get a reference to the collection with a specific category document id
        this.bagger = db.collection("users").document(bagger);
    }

    public void setCustomer(String customer){
        //database connection
        Firestore db = FirestoreClient.getFirestore();
        //get a reference to the collection with a specific category document id
        this.customer = db.collection("users").document(customer);
    }

    public void setProduct(String product){
        //database connection
        Firestore db = FirestoreClient.getFirestore();
        //get a reference to the collection with a specific category document id
        this.product = db.collection("product").document(product);
    }
}
