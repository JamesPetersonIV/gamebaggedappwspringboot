package edu.famu.gamebaggedappwspringboot.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.Chat;
import edu.famu.gamebaggedappwspringboot.models.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ChatService {
    public ArrayList<Chat> retrieveChatByUsers(String id) throws ExecutionException, InterruptedException {
        ArrayList<Chat> list = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve multiple documents
        DocumentReference cusRef = db.collection("users").document(id);

        //Allows us to make an async call to the database
        ApiFuture<QuerySnapshot> future = db.collectionGroup("orders").whereEqualTo("customer", cusRef).get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot document : documents)
        {
            Users customer = new Users();

            //ApiFuture allows us to make async calls to the database
            ApiFuture<DocumentSnapshot> cusFuture = cusRef.get();
            //Retrieve document
            DocumentSnapshot cusDoc = cusFuture.get();

            if(cusDoc.exists())
                customer = cusDoc.toObject(Users.class);
            //add the admin to the list
            list.add(new Chat(document.getId(), document.getString("sentAt"), document.getString("sentBy"), document.getString("chatText"), document.getString("post"), customer));

        }

        return list;
    }
}
