package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class AbstractChat {
    @DocumentId
    protected String id;
    protected String post;
    protected String sentAt;
    protected String sentBy;
    protected String chatText;
}
