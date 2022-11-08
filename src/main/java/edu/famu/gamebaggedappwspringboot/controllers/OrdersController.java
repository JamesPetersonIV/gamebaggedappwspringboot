package edu.famu.gamebaggedappwspringboot.controllers;

import edu.famu.gamebaggedappwspringboot.models.Orders;
import edu.famu.gamebaggedappwspringboot.services.OrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/orders")
@RestController
public class OrdersController {

    OrdersService ordersService;

    public OrdersController(OrdersService service) {
        this.ordersService = service;
    }

    //Get orders by bagger id
    @GetMapping ("/bagger/{id}")
    public ArrayList<Orders> getOrdersByBagger(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return ordersService.retrieveItemsByOrders(id);
    }
}
