package com.zeus.sample.providerapi;

import com.zeus.register.base.IRpcService;

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
