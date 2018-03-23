package com.zeus.test;

import com.zeus.client.ZeusClient;
import com.zeus.provider.ZeusApi;
import com.zeus.provider.ZeusApiImpl;
import com.zeus.register.ServiceCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by buzheng on 18/3/23.
 */
@RestController
@RequestMapping("/rpc")
public class RpcController {

    @Autowired
    private static ServiceCenter serviceCenter;

    @RequestMapping("/test")
    private String testRpc(){
        serviceCenter.register(ZeusApi.class, ZeusApiImpl.class);
        ZeusApi zeusApi = null;
        try {
            zeusApi = ZeusClient.getRemoteProxy(ZeusApi.class, new InetSocketAddress(InetAddress.getLocalHost(), 9999));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String s = zeusApi.zeus();
        return s;
    }
}
