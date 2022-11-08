package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //creates setters and getters automatically
@AllArgsConstructor //creates constructor with all values automatically
@NoArgsConstructor //creates no argument constructor automatically

public class Users {
    @DocumentId //denotes that this variable is storing the document id value
    private String id;
    private String name;
    private String role;
    private String uid;
}
