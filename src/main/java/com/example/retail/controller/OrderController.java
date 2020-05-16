package com.example.retail.controller;


import com.example.retail.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.retail.model.Order;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(value = "/")
    public String index() {
        return "Greetings from Spring Boot";
    }

    @GetMapping(value = "/placeOrder")
    public long placeOrder(@RequestParam String itemName, @RequestParam int quantity) {
        Order order = new Order(itemName, quantity);
        return orderMapper.insertOrder(order);
    }

    @GetMapping(value = "/getOrderById")
    public Order getOrderById(@RequestParam int id) {
        return orderMapper.getOrderById(id);
    }
}