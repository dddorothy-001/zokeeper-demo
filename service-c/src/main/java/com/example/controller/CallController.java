package com.example.controller;

import com.example.caller.AbstractServiceCaller;
import com.example.caller.ServiceCallerFactory;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class CallController {

    private final ServiceCallerFactory factory;

    @Autowired
    public CallController(ServiceCallerFactory factory) {
        this.factory = factory;
    }
    @GetMapping("/{service}")
    public String call(@PathVariable String service) throws Exception {
        AbstractServiceCaller caller = factory.getCaller(service);
        if (caller == null) {
            return "unknown";
        }
        return caller.call("/hello");
    }
}
