package com.simple.framework;

import com.alibaba.fastjson.JSON;
import com.simple.framework.protocol.http.HttpClient;
import com.simple.service.ComputeService;
import jdk.nashorn.internal.parser.JSONParser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {


    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient = new HttpClient("http", "localhost", 9090);
                Invocation invocation = new Invocation(
                        interfaceClass.getName(),
                        method.getName(),
                        method.getParameterTypes(),
                        args
                );
                System.out.println("方法返回类型"+ method.getReturnType());
                String send = httpClient.send(invocation);
                return JSON.parseObject(send,method.getReturnType());
            }
        });
    }

}
