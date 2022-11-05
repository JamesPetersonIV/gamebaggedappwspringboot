package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class abstractOrders {

        @DocumentId
        private HashMap cashout;
        private String customer;
        private HashMap deliverywindow;
        private HashMap destinations;
        private String product;
        private String store;
}
