package com.example;

import jakarta.annotation.PostConstruct;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.springframework.stereotype.Component;

@Component
public class ServiceRegistry {

    private final ServiceDiscovery<Void> discovery;

    public ServiceRegistry(CuratorFramework client) throws Exception {
        ServiceInstance<Void> instance = ServiceInstance.<Void>builder()
                .name("service-a")
                .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
                .address("localhost") // Docker部署时请写容器名或内网IP
                .port(9090)
                .build();

        this.discovery = ServiceDiscoveryBuilder.builder(Void.class)
                .client(client)
                .basePath("/services")
                .thisInstance(instance)
                .build();
    }

    @PostConstruct
    public void register() throws Exception {
        discovery.start();
    }
}

