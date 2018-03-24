package com.zeus;

import com.zeus.client.ZeusClient;
import com.zeus.register.register.RegisterCenter;
import com.zeus.sample.providerapi.ZeusApi;
import com.zeus.sample.providerimpl.ZeusApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by buzheng on 18/3/24.
 */
@RestController
@RequestMapping("/rpc")
public class RpcController {

    @Autowired
    private RegisterCenter registerCenter;

    @RequestMapping("/test")
    private String testRpc()throws Exception{
        registerCenter.register(ZeusApi.class, ZeusApiImpl.class);
        ZeusApi zeusApi = null;
        try {
            zeusApi = ZeusClient.getRemoteProxy(ZeusApi.class, new InetSocketAddress(InetAddress.getLocalHost(), 9999));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String s = zeusApi.zeus();
        return "fff";
    }
}