package com.musiccrawl.crawl.qq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiccrawl.crawl.DefaultCrawl;
import com.musiccrawl.entity.PlayList;
import net.dongliu.requests.Requests;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * QQ音乐歌单爬取
 * Created by Administrator on 2017/10/21.
 */
public class QQCrawl extends DefaultCrawl{

    private final String BASE_URL = "https://y.qq.com/n/yqq/playlist/";

    public List<PlayList> getPlayList(String url,int curr){
        List<PlayList> lists = new ArrayList<>();
        String result = Requests.get(url).headers(initHeader()).params(initParam(curr)).send().readToText();
        result = result.replace("getPlaylist(","").replace(")","");
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
        for(int i=0 ;i<list.size();i++){
            JSONObject object = list.getJSONObject(i);
            PlayList playList = new PlayList();
            playList.setImgUrl(object.getString("imgurl"));
            playList.setTitle(object.getString("dissname"));
            playList.setUrl(BASE_URL+object.getString("dissid")+".html");
            playList.setCount(object.getString("listennum"));
            lists.add(playList);
        }
        return lists;
    }

    // 初始化头部信息 模拟浏览器
    private Map<String ,String> initHeader(){
        Map<String,String > header = new HashMap<>();
        header.put("referer","http://y.qq.com/");
        header.put("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");

        return header;
    }

    private Map<String ,String> initParam(int curr){
        Map<String ,String> param = new HashMap<>();
        param.put("picmid","1");
        param.put("g_tk","775743552");
        param.put("jsonpCallback","getPlaylist");
        param.put("hostUin","0");
        param.put("format","jsonp");
        param.put("inCharset","utf8");
        param.put("outCharset","utf-8");
        param.put("notice","0");
        param.put("platform","yqq");
        param.put("needNewCode","0");
        param.put("categoryId","10000000");
        param.put("sortId","5");
        param.put("sin",curr+"");
        param.put("ein",curr+29+"");
        return param;
    }

}
