package edu.famu.gamebaggedappwspringboot.models;


import com.google.cloud.firestore.GeoPoint;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashMap;

@Data
@NoArgsConstructor
public class Orders extends AbstractOrders {

    private Users customer;
    private Users bagger;
    private Product product;

    public Orders( String id, HashMap <Double,Double> cashout, HashMap <Timestamp, Timestamp> deliverywindow, HashMap <GeoPoint, GeoPoint> destinations, String store, Users bagger, Users customer, Product product) {
        super(id, cashout, deliverywindow, destinations, store); //calling parent constructor
        this.bagger = bagger;
        this.customer = customer;
        this.product = product;
    }

}
