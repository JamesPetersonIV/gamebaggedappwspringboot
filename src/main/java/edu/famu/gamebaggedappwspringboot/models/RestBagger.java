package edu.famu.gamebaggedappwspringboot.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

//TODO: You need to add the get method. I added the missing data member the set method.
public class RestBagger extends AbstractBagger{
    
    private DocumentReference bagger; //added by V.C.
    
    public RestBagger(String email, String uid, String firstName, String lastName) {
        super(email, uid, firstName, lastName); //calling parent constructor
    }
    //added by V.C.
    public void setBagger(DocumentReference bagger) {
        this.bagger = bagger;
    }

}
