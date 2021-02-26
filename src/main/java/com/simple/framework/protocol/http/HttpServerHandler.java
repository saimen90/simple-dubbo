package com.simple.framework.protocol.http;


import com.alibaba.fastjson.JSONObject;
import com.simple.framework.Invocation;
import com.simple.framework.register.LocalRegister;
import com.simple.framework.until.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * HttpServer 服务
 */
public class HttpServerHandler {

    // 接收请求 处理逻辑
        /*
          1、接口名
          2、方法名
          3、方法参数类型列表
          4、方法值列表
         */
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        System.out.printf("服务处理");
        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            // 根据接口名获取实现类( 修改： 缓存 + zookeper )
            Class implClass = LocalRegister.get(invocation.getInterfaceName());
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            // 执行方法
            Object result = method.invoke(implClass.newInstance(), invocation.getParams());
            // 返回执行结果
            HttpResponse.write(resp, result);
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
