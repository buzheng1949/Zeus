package com.zeus.provider;

import com.zeus.register.IRpcService;

/**
 * Created by buzheng on 18/3/23.
 * 对外暴露的接口
 */
public interface ZeusApi extends IRpcService {
    /**
     * 测试接口
     *
     * @return
     */
    String zeus();
}
