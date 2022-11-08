package edu.famu.gamebaggedappwspringboot.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.gamebaggedappwspringboot.models.AdminChat;
import edu.famu.gamebaggedappwspringboot.models.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AdminChatService {
    public ArrayList<AdminChat> retrieveAdminChatByUsers(String id) throws ExecutionException, InterruptedException {
        ArrayList<AdminChat> list = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //asynchronously retrieve multiple documents
        DocumentReference adRef = db.collection("users").document(id);

        //Allows us to make an async call to the database
        ApiFuture<QuerySnapshot> future = db.collectionGroup("orders").whereEqualTo("admin", adRef).get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot document : documents)
        {
            Users admin = new Users();

            //ApiFuture allows us to make async calls to the database
            ApiFuture<DocumentSnapshot> adFuture = adRef.get();
            //Retrieve document
            DocumentSnapshot adDoc = adFuture.get();

            if(adDoc.exists())
                admin = adDoc.toObject(Users.class);
            //add the admin to the list
            list.add(new AdminChat(document.getId(), document.getString("sentAt"), document.getString("sentBy"), document.getString("chatText"), document.getString("post"), admin));

        }

        return list;
    }
}
