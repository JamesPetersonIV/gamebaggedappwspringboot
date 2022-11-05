package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class customer {
    @DocumentId
    protected String id;
    protected String uid;
    protected String firstName;
    protected String lastName;
    protected ArrayList<String> paymentsMethods;

    public void addMethod(String paymentId)
    {
        paymentsMethods.add(paymentId);
    }

    public void setPaymentMethods(ArrayList<String> methods)
    {
        paymentsMethods = methods;
    }
    public ArrayList<String> getPaymentMethods(){
        return paymentsMethods;
    }
}
