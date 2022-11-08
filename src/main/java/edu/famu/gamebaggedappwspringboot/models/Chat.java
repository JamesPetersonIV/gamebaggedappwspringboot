package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;

@Data
@NoArgsConstructor

public class Chat extends AbstractChat{

    private Users customer;

    public Chat(String id, String sentAt, String sentBy, String chatText, String post, Users customer) {
        super(id, sentAt, sentBy, chatText, post); //calling parent constructor
        this.customer = customer;
    }

}
