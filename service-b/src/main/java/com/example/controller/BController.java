package com.example.controller;

import com.example.ServiceConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BController {

    private final ServiceConsumer consumer;

    public BController(ServiceConsumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping("/invoke-a")
    public String invoke() throws Exception {
        return consumer.callServiceA();  // 发起服务调用
    }
}

