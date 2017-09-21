package com.musiccrawl.crawl.wangyiyun;

import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.util.HttpGetUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * 默认抓取类
 * Created by Administrator on 2017/9/16.
 */
public abstract class DefaultCrawl implements ICrawl {

    @Override
    public String getPlayListByUrl(String url) throws Exception{
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpGet get = new HttpGetUtil(url);
            httpResponse = httpClient.execute(get);
            String result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            httpResponse.close();
            httpClient.close();
        }

    }
}
