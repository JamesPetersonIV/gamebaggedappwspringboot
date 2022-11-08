package edu.famu.gamebaggedappwspringboot.models;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class AbstractOrders {

        @DocumentId
        protected String id;
        protected HashMap cashout;
        protected HashMap deliverywindow;
        protected HashMap destinations;
        protected String store;
}
