package com.simple.consumer;

import com.simple.framework.Invocation;
import com.simple.framework.protocol.http.HttpClient;
import com.simple.service.ComputeService;

/**
 * ConsumerMain 消费者
 */
public class ConsumerMain {

    public static void main(String[] args) {

        HttpClient httpClient = new HttpClient("http", "localhost", 9090);
        Invocation invocation = new Invocation(
                ComputeService.class.getName(),
                "sum",
                new Class[]{Integer.class, Integer.class},
                new Object[]{11, 6}
        );
        String send = httpClient.send(invocation);
        System.out.printf("消费者 - ConsumerMain");
        System.out.printf(send);
    }
}
