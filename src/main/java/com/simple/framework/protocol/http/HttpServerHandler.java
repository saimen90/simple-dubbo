package com.simple.framework.protocol.http;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServer 服务
 */
public class HttpServerHandler {


    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        System.out.printf("服务处理");
        System.out.printf(req.getRequestURI());
        // 接收请求 处理逻辑
        /*
          1、接口名
          2、方法名
          3、方法参数类型列表
          4、方法值列表
         */
    }

}
