package com.example.caller;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ServiceCallerFactory {

    private final Map<String, AbstractServiceCaller> callerMap = new HashMap<>();

    @Autowired
    public ServiceCallerFactory(CuratorFramework client) throws Exception {
        ServiceDiscovery<Void> discovery = ServiceDiscoveryBuilder.builder(Void.class)
                .client(client)
                .basePath("/services")
                .build();

        discovery.start();

        ServiceProvider<Void> providerA = discovery.serviceProviderBuilder().serviceName("service-a").build();

        ServiceProvider<Void> providerB = discovery.serviceProviderBuilder().serviceName("service-b").build();

        callerMap.put("service-a", new ServiceACaller(providerA));
        callerMap.put("service-b", new ServiceBCaller(providerB));

        providerA.start();
        providerB.start();
    }

    public AbstractServiceCaller getCaller(String serviceName) {
        return this.callerMap.get(serviceName);
    }



}
