package com.example;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceConsumer {

    private final ServiceProvider<Void> provider;

    public ServiceConsumer(CuratorFramework client) throws Exception {
        ServiceDiscovery<Void> discovery = ServiceDiscoveryBuilder.builder(Void.class)
                .client(client)
                .basePath("/services")
                .build();

        discovery.start();

        this.provider = discovery.serviceProviderBuilder()
                .serviceName("service-a")
                .build();

        this.provider.start();
    }

    public String callServiceA() throws Exception {
        ServiceInstance<Void> instance = provider.getInstance(); // 从 ZooKeeper 获取 Service A 实例
        String url = instance.buildUriSpec().toString() + "/hello";
        return new RestTemplate().getForObject(url, String.class); // 远程调用
    }
}

