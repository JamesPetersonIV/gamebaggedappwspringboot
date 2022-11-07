package edu.famu.gamebaggedappwspringboot.services;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.Bagger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class BaggerService {
    public ArrayList<Bagger> getCategories() throws ExecutionException, InterruptedException {
        ArrayList<Bagger> baggers = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection("bagger").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot document : documents)
        {
            baggers.add(document.toObject(Bagger.class));
        }

        return baggers;

    }

    public Bagger getBagger(String email) throws ExecutionException, InterruptedException {
        Bagger bagger = new Bagger();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve document
        DocumentReference docRef = db.collection("bagger").document(email);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();

        // future.get() blocks on response
        DocumentSnapshot document = future.get();

        if (document.exists())
            bagger = document.toObject(Bagger.class);

        return bagger;
    }
}
