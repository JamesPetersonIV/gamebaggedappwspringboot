package edu.famu.gamebaggedappwspringboot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Bagger extends AbstractBagger {
    public Bagger(String email, String uid, String firstName, String lastName) {
        super(email, uid, firstName, lastName); //calling parent constructor
    }
}
