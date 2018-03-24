package com.zeus.register.discover;

import com.zeus.register.register.RegisterCenter;

/**
 * Created by buzheng on 18/3/24.
 */
public interface Discover {
    Class<?> getServiceProvider (String serviceName);
}
