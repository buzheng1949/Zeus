package com.zeus.client.proxy;

import com.zeus.register.base.IRpcService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by buzheng on 18/3/23.
 * 动态代理类
 */
public class RpcProxyHandler<T extends IRpcService> implements InvocationHandler {

    private Class<? extends IRpcService> service;

    private InetSocketAddress addr;

    public RpcProxyHandler(Class<? extends IRpcService> service, InetSocketAddress addr) {
        this.service = service;
        this.addr = addr;
    }

    public <T> T getProxyInstance() {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            socket = new Socket();
            socket.connect(addr);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeUTF(service.getName());
            outputStream.writeUTF(method.getName());
            outputStream.writeObject(method.getParameterTypes());
            outputStream.writeObject(args);
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }

        }
        return inputStream.readObject();
    }
}