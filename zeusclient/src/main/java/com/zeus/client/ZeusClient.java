package com.zeus.client;

import com.zeus.client.proxy.RpcProxyHandler;
import com.zeus.register.base.IRpcService;

import java.net.InetSocketAddress;

/**
 * Created by buzheng on 18/3/23.
 * 动态代理获取远端接口
 */
public class ZeusClient {
    public static <T extends IRpcService> T getRemoteProxy(Class<? extends IRpcService> serviceInterface, InetSocketAddress addr) {
        RpcProxyHandler proxyHandler = new RpcProxyHandler(serviceInterface, addr);
        return (T) proxyHandler.getProxyInstance();
    }
}
