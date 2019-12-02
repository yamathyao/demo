package com.example.demo.service;

import com.example.demo.annotation.AopLogs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author GEEX177
 * @date 2019/10/24
 */
@Slf4j
@Service
public class DemoService {

    @AopLogs("test")
    public void goTest(String[] args) {
        log.info("test over");
    }

    public void demo资金方开户() {
        String fund = "考拉";
        String action = "开户";
        String errorCode = "1096";
        // 数据准备
        try {
            // logic
            log.warn("缺少{}", "参数");
        } catch (Exception ex) {
            log.error("数据准备失败", ex);
            return;
        }
        // 开户
        try {
            // http
            // response

        } catch (Exception ex) {
            // timeout
            // response error
            log.error("Fund:{},Action:{},ErrorCode:{}, Ex:{}", fund, action, errorCode, "这里是错误消息" + ex.getMessage(), ex);
        }
    }
}
