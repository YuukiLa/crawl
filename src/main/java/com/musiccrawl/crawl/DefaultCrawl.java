package com.musiccrawl.crawl;

import com.musiccrawl.crawl.interfacies.ICrawl;
import net.dongliu.requests.Requests;

/**
 * 基础抓取类
 * 抓取页面数据
 * Created by Administrator on 2017/9/16.
 */
public abstract class DefaultCrawl implements ICrawl {

    @Override
    public String getContentByUrl(String url) throws Exception{
        try {
            String result = Requests.get(url).send().readToText();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
