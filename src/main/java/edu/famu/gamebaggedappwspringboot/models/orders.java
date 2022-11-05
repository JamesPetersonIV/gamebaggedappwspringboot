package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.GeoPoint;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashMap;

@Data
@NoArgsConstructor
public class orders extends abstractOrders {
    private bagger Bagger;

    public orders(HashMap<Double,Double> cashout, String customer, HashMap<Timestamp, Timestamp> deliverywindow, HashMap<GeoPoint,GeoPoint> destinations, String product, String store,bagger Bagger) {
        super(cashout, customer, deliverywindow, destinations, product, store); //calling parent constructor
        this.Bagger = Bagger;
    }
}
