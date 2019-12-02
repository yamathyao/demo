package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
@Data
public class OrderInfoModel implements Serializable {

    private String orderId;

    private String productName;

    private String status;

    public OrderInfoModel(String orderId, String productName, String status) {
        this.orderId = orderId;
        this.productName = productName;
        this.status = status;
    }
}
