package com.musiccrawl.crawl.qq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiccrawl.crawl.DefaultCrawl;
import com.musiccrawl.common.entity.PlayList;
import com.musiccrawl.common.entity.Song;
import com.musiccrawl.common.myexception.FailedCrawlResultException;
import net.dongliu.requests.Requests;

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
    private final String PLAY_LIST_URL = "https://c.y.qq.com/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg";
    private final String LYRIC_URL = "http://i.y.qq.com/lyric/fcgi-bin/fcg_query_lyric.fcg";
    public Map<String ,Object> getPlayList(String url,int curr){
        return getPlayList(url,curr,10000000,3);
    }

    public Map<String ,Object> getPlayList(String url,int curr,int categoryId,int type){
        try {
            Map<String, Object> resultMap = new HashMap<>();
            List<PlayList> lists = new ArrayList<>();
            Map<String, String> param = initParam();
            param.put("sortId", "5");
            param.put("sin", curr + "");
            param.put("ein", curr + 29 + "");
            param.put("jsonpCallback", "getPlaylist");
            param.put("categoryId",categoryId+"");
            String result = Requests.get(url).headers(initHeader()).params(param).send().readToText();
            //  System.out.println(result);
            result = result.replace("getPlaylist(", "").replace(")", "");
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
            if(list.isEmpty()){
                throw new FailedCrawlResultException();
            }
            for (int i = 0; i < list.size(); i++) {
                JSONObject object = list.getJSONObject(i);
                PlayList playList = new PlayList();
                playList.setId(object.getString("dissid"));
                playList.setImgUrl(object.getString("imgurl"));
                playList.setTitle(object.getString("dissname"));
                playList.setUrl(BASE_URL + object.getString("dissid") + ".html");
                playList.setCount(object.getString("listennum"));
                playList.setPlatformCode(3);
                playList.setType(type);
                lists.add(playList);
            }
            resultMap.put("playList", lists);
            resultMap.put("nextUrl", ++curr);
            return resultMap;
        }catch (Exception e){
            throw new FailedCrawlResultException("未抓取到数据");
        }
    }
    public List<Song> getSongs(String url){
        return getSongs(url,1);
    }
    public List<Song> getSongs(String url,int type){
        List<Song> songs = new ArrayList<>();
        try {


            Map<String, String> param = initParam();
            String s = url.split("playlist/")[1];
            s = s.split(".ht")[0];
            param.put("disstid", s);
            param.put("onlysong", "1");
            param.put("jsonpCallback", "playlistinfoCallback");
            param.put("onlysong", "0");
            param.put("type", "1");
            param.put("json", "1");
            param.put("utf8", "1");
            String result = Requests.get(PLAY_LIST_URL).headers(initHeader()).params(param).send().readToText();
            result = result.replace("playlistinfoCallback(", "").replace(")", "");
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray list = jsonObject.getJSONArray("cdlist").getJSONObject(0).getJSONArray("songlist");
            for (int i = 0; i < list.size(); i++) {
                JSONObject object = list.getJSONObject(i);
                Song song = new Song();
                song.setId(object.getString("songmid"));
                song.setName(object.getString("songname"));
//            song.setUrl(getSongUrl(object.getString("songmid")));
                song.setUrl(String.format("http://ws.stream.qqmusic.qq.com/C100%s.m4a?fromtag=46", song.getId()));
                song.setImgUrl(String.format("https://y.gtimg.cn/music/photo_new/T002R300x300M000%s.jpg?max_age=2592000", object.getString("albummid")));
                song.setSinger(getSinger(object.getJSONArray("singer")));
                song.setLrcText(getLyric(object.getString("songid")));
                song.setPlantformCode(3);
                song.setType(type);
                songs.add(song);
            }
        }catch (Exception e){

        }
        return songs;
    }
    // 获取歌词
    private String getLyric(String id){
        Map<String,String > param = initParam();
        param.put("musicid",id);
        param.put("jsonpCallback","jsonp1");
        param.put("nobase64","1");
        param.put("g_tk","5381");
        String text = Requests.get(LYRIC_URL).headers(initHeader()).params(param).send().readToText();
        text = text.replace("jsonp1(","");
        text = text.substring(0,text.length()-1);
        JSONObject object = JSONObject.parseObject(text);
        return object.getString("lyric");
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
    private String getSinger(JSONArray array){
        StringBuilder singer = new StringBuilder();
        for(int i =0;i<array.size();i++){
            JSONObject object = array.getJSONObject(i);
            singer.append(object.getString("name"));
            singer.append("/");
        }
        return singer.deleteCharAt(singer.length()-1).toString();
    }

}
