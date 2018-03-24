package com.zeus.register.register;

import com.zeus.register.base.IRpcService;
import com.zeus.register.task.Task;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.*;

/**
 * Created by buzheng on 18/3/23.
 * 模拟zookeeper做一个配置中心 实现服务注册功能
 */
@Component
public class RegisterCenter implements Register {

    private boolean isEndLoop = false;

    /**
     * 服务线程池执行
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 200, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

    /**
     * 一个注册服务的缓存注册表 类似Spring的单例注册表
     */
    public static final ConcurrentHashMap<String, Class<? extends IRpcService>> registerFactory = new ConcurrentHashMap<>();

    @Override
    public void destroy() throws Exception {
        stop();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }

    @Override
    public void start() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(PORT));
            while (!isEndLoop) {
                threadPoolExecutor.execute(new Task(serverSocket.accept()));
            }
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }


    }

    @Override
    public void stop() throws IOException {
        /**
         * 执行完任务后再关闭容器
         */
        threadPoolExecutor.shutdown();
    }

    @Override
    public void register(Class<? extends IRpcService> iRpcService, Class<? extends IRpcService> IRpcServiceImpl) {
        registerFactory.put(iRpcService.getName(), IRpcServiceImpl);
    }
}
