package edu.famu.gamebaggedappwspringboot.auth.models;

import edu.famu.gamebaggedappwspringboot.models.Users;
import lombok.Data;

@Data
public class FirebaseUser {
    private String uid;
    private String name;
    private String issuer;
    private String picture;
    private String email;
    private boolean emailVerified;
    private Users users;

}
