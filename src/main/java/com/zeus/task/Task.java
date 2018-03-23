package com.zeus.task;

import com.zeus.register.ServiceCenter;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by buzheng on 18/3/23.
 * 方法请求
 */
public class Task implements Runnable {

    Socket client;

    public Task(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(client.getInputStream());
            String serviceName = inputStream.readUTF();
            String methodName = inputStream.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
            Object[] arguments = (Object[]) inputStream.readObject();
            Class<?> serviceProvider = ServiceCenter.registerFactory.get(serviceName);
            Method method = serviceProvider.getMethod(methodName, parameterTypes);
            Object result = method.invoke(serviceProvider.newInstance(), arguments);
            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
