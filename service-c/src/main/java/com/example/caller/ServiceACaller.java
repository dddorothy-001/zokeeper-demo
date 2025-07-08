package com.example.caller;

import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;

public class ServiceACaller extends AbstractServiceCaller {
    public ServiceACaller(ServiceProvider<Void> provider) {
        super(provider);
    }
}
