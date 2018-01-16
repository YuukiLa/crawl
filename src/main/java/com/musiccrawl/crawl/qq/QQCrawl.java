package com.musiccrawl.crawl.qq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiccrawl.crawl.DefaultCrawl;
import com.musiccrawl.entity.PlayList;
import com.musiccrawl.entity.Song;
import net.dongliu.requests.Requests;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * QQ音乐歌单爬取
 * Created by Administrator on 2017/10/21.
 */
public class QQCrawl extends DefaultCrawl{

    private final String BASE_URL = "https://y.qq.com/n/yqq/playlist/";
    private final String PLAY_LIST_URL = "https://c.y.qq.com/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg";

    public Map<String ,Object> getPlayList(String url,int curr){
        Map<String,Object> resultMap = new HashMap<>();
        List<PlayList> lists = new ArrayList<>();
        Map<String, String> param = initParam();
        param.put("sortId","5");
        param.put("sin",curr+"");
        param.put("ein",curr+29+"");
        param.put("jsonpCallback","getPlaylist");
        String result = Requests.get(url).headers(initHeader()).params(param).send().readToText();
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
        resultMap.put("playList",lists);
        resultMap.put("nextUrl",++curr);
        return resultMap;
    }

    public List<Song> getSongs(String url){
        List<Song> songs = new ArrayList<>();
        Map<String, String> param = initParam();
        String s = url.split("playlist/")[1];
        s = s.split(".ht")[0];
        System.out.println(s);
        param.put("disstid",s);
        param.put("onlysong","1");
        param.put("jsonpCallback","playlistinfoCallback");
        param.put("onlysong","0");
        param.put("type","1");
        param.put("json","1");
        param.put("utf8","1");
        String result = Requests.get(PLAY_LIST_URL).headers(initHeader()).params(param).send().readToText();
        result = result.replace("playlistinfoCallback(","").replace(")","");
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray list = jsonObject.getJSONArray("cdlist").getJSONObject(0).getJSONArray("songlist");
        for(int i=0 ;i<list.size();i++){
            JSONObject object = list.getJSONObject(i);
            Song song = new Song();
            song.setName(object.getString("songname"));
            song.setUrl(getSongUrl(object.getString("songmid")));
            songs.add(song);
        }
        return songs;
    }

    // 获取歌曲链接
    public String getSongUrl(String mid){
        Map<String, String> param = initParam();
        param.put("songmid",mid);
        param.put("jsonpCallback","MusicJsonCallback");
        param.put("callback","MusicJsonCallback");
        param.put("onlysong","0");
        param.put("guid","3982292630");
        param.put("hostUin","0");
        param.put("cid","cid");
        String s = Requests.get("https://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg").headers(initHeader()).params(param).send().readToText();
        s = s.replace("MusicJsonCallback(", "").replace(")", "");
        JSONObject jsonObject = JSONObject.parseObject(s);
        s = (String) jsonObject.getJSONObject("url").entrySet().iterator().next().getValue();
        return s;
    }



    // 初始化头部信息 模拟浏览器
    private Map<String ,String> initHeader(){
        Map<String,String > header = new HashMap<>();
        header.put("referer","http://y.qq.com/");
        header.put("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");

        return header;
    }
    // 初始化参数  共同部分
    private Map<String ,String> initParam(){
        Map<String ,String> param = new HashMap<>();
        param.put("picmid","1");
        param.put("g_tk","775743552");
        param.put("hostUin","0");
        param.put("format","jsonp");
        param.put("inCharset","utf8");
        param.put("outCharset","utf-8");
        param.put("notice","0");
        param.put("platform","yqq");
        param.put("needNewCode","0");
        param.put("categoryId","10000000");
        return param;
    }

}
