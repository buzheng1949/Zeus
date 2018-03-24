package com.zeus.register.discover;

import com.zeus.register.register.RegisterCenter;
import org.springframework.stereotype.Component;

/**
 * Created by buzheng on 18/3/24.
 */
@Component
public class DiscoverCenter implements Discover {

    @Override
    public Class<?> getServiceProvider(String serviceName) {
        Class<?> serviceProvider = RegisterCenter.registerFactory.get(serviceName);
        return serviceProvider;
    }
}
