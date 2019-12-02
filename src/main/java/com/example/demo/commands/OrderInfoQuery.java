package com.example.demo.commands;

import com.example.demo.cqs.Query;
import com.example.demo.model.OrderInfoModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
@Getter
@Setter
public class OrderInfoQuery implements Query<OrderInfoModel> {
    private String orderId;

    private String productName;

    private String status;
}
