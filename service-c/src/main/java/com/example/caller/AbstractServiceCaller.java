package com.example.caller;

import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractServiceCaller {

    protected final ServiceProvider<Void> provider;

    public AbstractServiceCaller(ServiceProvider<Void> provider) {
        this.provider = provider;
    }

    public String call(String path) throws Exception {
        ServiceInstance<Void> instance = selectInstance();
        String url = instance.buildUriSpec().toString() + path;
        return new RestTemplate().getForObject(url, String.class);
    }

    protected ServiceInstance<Void> selectInstance() throws Exception {
        return provider.getInstance();
    }
}
