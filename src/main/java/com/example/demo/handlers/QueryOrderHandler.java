package com.example.demo.handlers;

import com.example.demo.commands.OrderInfoQuery;
import com.example.demo.cqs.QueryHandler;
import com.example.demo.model.OrderInfoModel;
import org.springframework.stereotype.Component;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
@Component
public class QueryOrderHandler implements QueryHandler<OrderInfoModel, OrderInfoQuery> {
    @Override
    public OrderInfoModel handle(OrderInfoQuery command) {
        return new OrderInfoModel(command.getOrderId(), command.getProductName(), command.getStatus());
    }
}
