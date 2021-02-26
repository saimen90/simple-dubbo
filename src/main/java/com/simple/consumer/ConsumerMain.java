package com.simple.consumer;

import com.simple.framework.Invocation;
import com.simple.framework.ProxyFactory;
import com.simple.framework.protocol.http.HttpClient;
import com.simple.service.ComputeService;

/**
 * ConsumerMain 消费者
 */
public class ConsumerMain {

    public static void main(String[] args) {

        ComputeService computeService = ProxyFactory.getProxy(ComputeService.class);
        int sum = computeService.sum(66, 66);
        System.out.printf("动态代理计算结果：" + sum);

        String say = computeService.say(" 张三 ");
        System.out.printf("动态代理返回结果>>>>>" + say);

        HttpClient httpClient = new HttpClient("http", "localhost", 9090);
        Invocation invocation = new Invocation(
                ComputeService.class.getName(),
                "sum",
                new Class[]{Integer.class, Integer.class},
                new Object[]{6, 6}
        );
        String send = httpClient.send(invocation);
        System.out.printf("消费者 - ConsumerMain\n");
        System.out.printf("计算结果：" + send);
    }
}
