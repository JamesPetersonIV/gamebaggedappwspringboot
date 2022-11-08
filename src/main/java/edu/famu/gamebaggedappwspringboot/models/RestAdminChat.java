package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class RestAdminChat extends AbstractAdminChat {

    public RestAdminChat(String id, String sentAt, String sentBy, String chatText, String post, DocumentReference admin) {
        super(id, sentAt, sentBy, chatText, post); //calling parent constructor
        this.admin = admin;
    }

    private DocumentReference admin;

    public DocumentReference getAdmin() {
        return admin;
    }

    public void setAdmin (String admin) {
        //
        Firestore db = FirestoreClient.getFirestore();
        //
        this.admin = db.collection("users").document(admin);
    }
}
