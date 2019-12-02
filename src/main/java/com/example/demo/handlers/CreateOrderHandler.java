package com.example.demo.handlers;

import com.example.demo.commands.CreateOrderCommand;
import com.example.demo.cqs.CommandHandler;
import com.example.demo.model.OrderInfoModel;
import org.springframework.stereotype.Component;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
@Component
public class CreateOrderHandler implements CommandHandler<OrderInfoModel, CreateOrderCommand> {
    @Override
    public OrderInfoModel handle(CreateOrderCommand command) {
        return new OrderInfoModel(command.getOrderId(), command.getProductName(), command.getStatus());
    }
}
