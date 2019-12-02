package com.example.demo.controller;

import com.example.demo.commands.CreateOrderCommand;
import com.example.demo.commands.OrderInfoQuery;
import com.example.demo.commands.UpdateOrderCommand;
import com.example.demo.cqs.Bus;
import com.example.demo.model.OrderInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private Bus bus;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<OrderInfoModel> createOrder(CreateOrderCommand command) {
        return ResponseEntity.ok(bus.executeCommand(command));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<OrderInfoModel> updateOrder(UpdateOrderCommand command) {
        return ResponseEntity.ok(bus.executeCommand(command));
    }

    @RequestMapping(value = "/query")
    public ResponseEntity<OrderInfoModel> queryOrder(OrderInfoQuery query) {
        return ResponseEntity.ok(bus.executeQuery(query));
    }
}
