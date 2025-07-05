package com.example.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {

    private final ZookeeperProperties properties;

    public ZookeeperConfig(ZookeeperProperties properties) {
        this.properties = properties;
    }

    @Bean
    public CuratorFramework curatorFramework() {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                properties.getConnectString(),
                new ExponentialBackoffRetry(1000, 3)
        );
        client.start();
        return client;
    }
}


