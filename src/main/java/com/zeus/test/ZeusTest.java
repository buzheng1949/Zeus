package com.zeus.test;

import com.zeus.client.ZeusClient;
import com.zeus.provider.ZeusApi;
import com.zeus.provider.ZeusApiImpl;
import com.zeus.register.ServiceCenter;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by buzheng on 18/3/23.
 */
public class ZeusTest {

    public static void main(String[] args) throws Exception {
        ServiceCenter serviceCenter = ServiceCenter.getServiceCenterInstance();
        serviceCenter.register(ZeusApi.class, ZeusApiImpl.class);
        serviceCenter.start();
    }
}
