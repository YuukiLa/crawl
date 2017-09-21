package com.musiccrawl.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * Created by Administrator on 2017/9/16.
 */
public class HttpGetUtil extends HttpGet {

    public HttpGetUtil() {
        super();
    }

    public HttpGetUtil(URI uri) {
        super(uri);
    }

    public HttpGetUtil(String uri) {
        super(uri);
        init();
    }

    private void init(){
        this.setConfig(RequestConfig.custom()
                .setConnectTimeout(10_000)
                .setConnectionRequestTimeout(10_000)
                .setSocketTimeout(2_000).build());
    }

}
