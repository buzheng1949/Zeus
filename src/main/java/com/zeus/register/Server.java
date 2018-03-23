package com.zeus.register;

import java.io.IOException;

/**
 * Created by buzheng on 18/3/23.
 */
public interface Server {

    /**
     * 服务端端口
     */
    Integer PORT = 9999;

    /**
     * 服务端启动
     *
     * @throws IOException
     */
    void start() throws IOException;

    /**
     * 停止服务端
     *
     * @throws IOException
     */
    void stop() throws IOException;

    /**
     * 服务启动注册
     *
     * @param iRpcService
     * @param IRpcServiceImpl
     */
    void register(Class<? extends IRpcService> iRpcService, Class<? extends IRpcService> IRpcServiceImpl);
}
