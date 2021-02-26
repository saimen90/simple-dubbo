package com.simple.framework.protocol.http.tomcat;

import com.simple.framework.protocol.http.HttpServerHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Tomcat服务
 * https://www.cnblogs.com/lmq-1048498039/p/8329481.html
 * <p>
 * https://www.cnblogs.com/mahuan2/p/6733566.html
 * https://blog.csdn.net/aimashi620/article/details/82106962
 * <p>
 * https://blog.csdn.net/huo920/article/details/82082403  => Maven配置教程
 */
public class DispatcherServer extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.service(req, resp);
        HttpServerHandler httpServerHandler = new HttpServerHandler();
        httpServerHandler.handler(req, resp);
    }
}