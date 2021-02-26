package com.simple.framework.protocol.http;

import com.simple.framework.protocol.http.tomcat.TomcatServer;
import org.apache.catalina.LifecycleException;

/**
 * HttpServer 服务
 */
public class HttpServer {


    public void start(String host, Integer port) {
        TomcatServer tomcatServer = new TomcatServer(host, port);
        try {
            tomcatServer.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

}
