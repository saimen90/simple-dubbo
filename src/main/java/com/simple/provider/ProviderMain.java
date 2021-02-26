package com.simple.provider;

import com.simple.framework.protocol.http.HttpServer;

/**
 * ProviderMain 提供者
 */
public class ProviderMain {

    public static void main(String[] args) {
        System.out.printf("提供者-ProviderMain");
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 9090);
    }
}
