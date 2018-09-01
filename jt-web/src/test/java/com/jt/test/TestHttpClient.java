package com.jt.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class TestHttpClient {
    @Test
    //测试get提交
    public void testGet() throws ClientProtocolException, IOException {
        String url ="http://item.jd.com/1221882.html";
        //定义请求对象
        HttpGet httpGet = new HttpGet(url);
        
        //定义httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //获取response对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //判断状态信息
        if(httpResponse.getStatusLine().getStatusCode()== 200){
            //表示请求正确，继续获取正确的信息 
            String data = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("testGet()data是："+data);
        }
    }
}
