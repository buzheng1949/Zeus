package com.zeus.client;

import com.zeus.proxy.ProxyHandler;
import com.zeus.register.IRpcService;

import java.net.InetSocketAddress;

/**
 * Created by buzheng on 18/3/23.
 * 动态代理获取远端接口
 */
public class ZeusClient {
    public static <T extends IRpcService> T getRemoteProxy(Class<? extends IRpcService> serviceInterface, InetSocketAddress addr) {
        ProxyHandler proxyHandler = new ProxyHandler(serviceInterface, addr);
        return (T) proxyHandler.getProxyInstance();
    }
}
