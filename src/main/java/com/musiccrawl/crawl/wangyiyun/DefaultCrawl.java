package com.musiccrawl.crawl.wangyiyun;

import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.util.HttpGetUtil;
import net.dongliu.requests.Request;
import net.dongliu.requests.Requests;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * 基础抓取类
 * 抓取页面数据
 * Created by Administrator on 2017/9/16.
 */
public abstract class DefaultCrawl implements ICrawl {

    @Override
    public String getPlayListByUrl(String url) throws Exception{

        try {
            String result = Requests.get(url).send().readToText();// EntityUtils.toString(httpResponse.getEntity());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
