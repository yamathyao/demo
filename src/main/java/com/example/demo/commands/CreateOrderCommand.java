package com.example.demo.commands;

import com.example.demo.cqs.Command;
import com.example.demo.model.OrderInfoModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
@Getter
@Setter
public class CreateOrderCommand implements Command<OrderInfoModel> {
    private String orderId;

    private String productName;

    private String status;

    public CreateOrderCommand() {
    }

    public CreateOrderCommand(String orderId, String productName, String status) {
        this.orderId = orderId;
        this.productName = productName;
        this.status = status;
    }
}
