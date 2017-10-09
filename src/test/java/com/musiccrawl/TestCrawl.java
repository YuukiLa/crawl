package com.musiccrawl;

import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.crawl.wangyiyun.WangyiyunCraw;
import com.musiccrawl.entity.PlayList;
import com.musiccrawl.util.WYYEncryptUtil;
import net.dongliu.requests.Requests;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/16.
 */
public class TestCrawl {

    @Test
    public void testGetPlayList(){
        ICrawl crawl = new WangyiyunCraw();
        try {
            String s = crawl.getPlayListByUrl("http://music.163.com/discover/playlist");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetPlayListAll(){
        WangyiyunCraw crawl = new WangyiyunCraw();
        List<PlayList> playList = crawl.getPlayList("http://music.163.com/discover/playlist/?cat=%E6%97%A5%E8%AF%AD");
        playList.stream().forEach(playList1 -> System.out.println(playList1));
    }

    private static final String userId = "32689809";

    private static Map<String, String> headers = new HashMap<>();

    static {
        headers.put("Referer", "http://music.163.com");
        headers.put("Origin", "http://music.163.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
    }
    @Test
    public void testApi(){
//        String data = "{\"s\":\"442315717\",\"offset\":0,\";limit\":1,\"type\":\"1\"}";
//
//        Map<String, String> forms = WYYEncryptUtil.encrypt(data);
//        String url = "http://music.163.com/weapi/cloudsearch/get/web";

        String data = "{\"ids\":"+ Arrays.asList("455345541")+",\"br\":320000,\"csrf_token\":\"\"}";
        Map<String, String> forms = WYYEncryptUtil.encrypt(data);
        System.out.println(forms.values());
        String url = "http://music.163.com/weapi/song/enhance/player/url?csrf_token=";
        String text = Requests.post(url).headers(headers).forms(forms).send().readToText();
        System.out.println(text);
//        PlayListResponse playListResponse = JSON.parseObject(text, PlayListResponse.class);
//        System.out.println(text);
    }


}
