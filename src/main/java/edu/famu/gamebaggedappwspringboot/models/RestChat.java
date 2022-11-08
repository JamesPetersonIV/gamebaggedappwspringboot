package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Collection;

public class RestChat extends AbstractChat {

    public RestChat(String id, String sentAt, String sentBy, String chatText, String post, DocumentReference customer) {
        super(id, sentAt, sentBy, chatText, post); //calling parent constructor
        this.customer = customer;
    }

    private DocumentReference customer;

    public DocumentReference getCustomer() {
        return customer;
    }

    public void setCustomer (String customer) {
        //
        Firestore db = FirestoreClient.getFirestore();
        //
        this.customer = db.collection("users").document(customer);
    }

}
