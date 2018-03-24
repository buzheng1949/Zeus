package com.zeus.sample.providerimpl;

import com.zeus.sample.providerapi.ZeusApi;

/**
 * Created by buzheng on 18/3/23.
 * 服务实现类
 */
public class ZeusApiImpl implements ZeusApi {

    private static final long serialVersionUID = 10086L;

    @Override
    public String zeus() {
        return "this is the zeus api impl";
    }
}
