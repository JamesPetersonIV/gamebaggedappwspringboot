package edu.famu.gamebaggedappwspringboot.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UsersService {
    //TODO: Change the method name
    public ArrayList<Users> getUsers() throws ExecutionException, InterruptedException {
        ArrayList<Users> users = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection("users").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        //loop over results and create user objects
        for(QueryDocumentSnapshot document : documents)
        {
            users.add(document.toObject(Users.class));
        }

        return users;

    }

    public Users getUsers(String id) throws ExecutionException, InterruptedException {
        Users users = new Users();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //TODO: This can't be written this way.Where you have email should be an id. If you want to query by email, you need to do it as a where clause
        //id serves as Document id
        //asynchronously retrieve document
        DocumentReference docRef = db.collection("users").document(id);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();

        // future.get() blocks on response
        DocumentSnapshot document = future.get();

        if (document.exists())
            users = document.toObject(Users.class);

        return users;
    }
}
