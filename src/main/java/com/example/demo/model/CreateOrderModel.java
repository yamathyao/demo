package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
@Data
public class CreateOrderModel implements Serializable {

    private String orderId;

    private String productName;

    private String status;
}
