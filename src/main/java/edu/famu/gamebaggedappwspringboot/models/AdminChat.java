package edu.famu.gamebaggedappwspringboot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor

public class AdminChat extends AbstractAdminChat {

    private Users admin;

    public AdminChat(String id, String sentAt, String sentBy, String chatText, String post, Users admin) {
        super(id, sentAt, sentBy, chatText, post); //calling parent constructor
        this.admin = admin;
    }

}
