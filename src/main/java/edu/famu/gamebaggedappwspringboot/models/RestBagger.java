package edu.famu.gamebaggedappwspringboot.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class RestBagger extends AbstractBagger{
    public RestBagger(String email, String uid, String firstName, String lastName) {
        super(email, uid, firstName, lastName); //calling parent constructor
    }

}
