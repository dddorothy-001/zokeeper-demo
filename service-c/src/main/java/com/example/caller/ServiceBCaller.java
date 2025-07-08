package com.example.caller;

import org.apache.curator.x.discovery.ServiceProvider;

public class ServiceBCaller extends AbstractServiceCaller{
    public ServiceBCaller(ServiceProvider<Void> provider) {
        super(provider);
    }
}
