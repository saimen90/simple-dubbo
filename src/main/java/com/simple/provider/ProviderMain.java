package com.simple.provider;

import com.simple.framework.protocol.http.HttpServer;
import com.simple.framework.register.LocalRegister;
import com.simple.provider.impl.ComputeServiceImpl;
import com.simple.provider.service.ComputeService;

/**
 * ProviderMain 提供者
 */
public class ProviderMain {

    public static void main(String[] args) {

        //注册服务
        LocalRegister.register(ComputeService.class.getName(), ComputeServiceImpl.class);

        System.out.printf("提供者-ProviderMain");
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 9090);
    }
}
