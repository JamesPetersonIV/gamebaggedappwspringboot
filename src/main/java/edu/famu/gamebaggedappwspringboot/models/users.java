package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //creates setters and getters automatically
@AllArgsConstructor //creates constructor with all values automatically
@NoArgsConstructor //creates no argument constructor automatically

public class users {
    @DocumentId //denotes that thie variable is storing the document id value
    private String name;
    private String role;
    private String uid;
}
