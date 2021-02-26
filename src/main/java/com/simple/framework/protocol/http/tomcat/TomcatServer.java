package com.simple.framework.protocol.http.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * Tomcat服务
 * https://www.cnblogs.com/lmq-1048498039/p/8329481.html
 * <p>
 * https://www.cnblogs.com/mahuan2/p/6733566.html
 * https://blog.csdn.net/aimashi620/article/details/82106962
 * <p>
 * https://blog.csdn.net/huo920/article/details/82082403  => Maven配置教程
 */
public class TomcatServer {

    private String host;
    private Integer port;

    public TomcatServer(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    // 42:19:
    public void start() throws LifecycleException {
        System.out.printf("tomcat 服务器启动");
        //1.把目录的绝对路径获取到
        String classPath = System.getProperty("user.dir");
        //2.新建一个Tomcat对象
        Tomcat tomcat = new Tomcat();
        //3.创建一个连接器
        Connector connector = tomcat.getConnector();
        //4.连接器有一个端口属性
        connector.setPort(this.port);
        //5.设置Host
        Host host = tomcat.getHost();
        //6.设置Host的属性，可以参照Server.xml来进行理解
        host.setName(this.host);
        //7.把class加载进来，把启动的工程加入进来了
        Context context = tomcat.addContext(host, "/", classPath);
        if (context instanceof StandardContext) {
            StandardContext standardContext = (StandardContext) context;
            //要给一个默认的web.xml文件
            //standardContext.setDefaultContextXml("D:\\web.xml");
            // 把server设置进去
            // Wrapper wrapper = tomcat.addServlet("/", "DispatcherServer", new DispatcherServer());
            // wrapper.addMapping("/nelson");
            tomcat.addServlet("/", "DispatcherServer", new DispatcherServer());
            context.addServletMapping("/*", "DispatcherServer");
        }
        tomcat.start();
        //强制Tomcat Server等待，避免main线程执行结束后关闭
        tomcat.getServer().await();
    }
}
