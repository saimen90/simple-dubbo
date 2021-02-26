package com.simple.framework.protocol.http;

import com.alibaba.fastjson.JSON;
import com.simple.framework.Invocation;
import com.simple.framework.protocol.http.tomcat.TomcatServer;
import org.apache.catalina.LifecycleException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpServer 服务
 */
public class HttpClient {

    private String scheme;
    private String hostname;
    private Integer port;

    public HttpClient(String scheme, String hostname, Integer port) {
        this.scheme = scheme;
        this.hostname = hostname;
        this.port = port;
    }


    /**
     * http 发送post请求
     *
     * @param invocation
     * @return
     */
    public String send(Invocation invocation) {

        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String uri = this.scheme + "://" + this.hostname + ":" + this.port;
        // 创建Post请求
        HttpPost httpPost = new HttpPost(uri);
        String jsonString = JSON.toJSONString(invocation);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                // System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                // System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                result = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
