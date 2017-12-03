package com.musiccrawl;

import com.alibaba.fastjson.JSONObject;
import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.crawl.qq.QQCrawl;
import com.musiccrawl.crawl.wangyiyun.WangyiyunCrawl;
import com.musiccrawl.crawl.xiami.XiamiCrawl;
import com.musiccrawl.entity.PlayList;
import com.musiccrawl.entity.Song;
import com.musiccrawl.util.WYYEncryptUtil;
import com.musiccrawl.util.XiamiDecodeUtil;
import net.dongliu.requests.Requests;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/16.
 */
public class TestCrawl {

    private static Map<String, String> headers = new HashMap<>();

    static {
        headers.put("Referer", "http://music.163.com");
        headers.put("Origin", "http://music.163.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
       // headers.put("Host","http://music.163.com");
    }


    @Test
    public void testGetPlayList() {
        ICrawl crawl = new WangyiyunCrawl();
        try {
            String s = crawl.getContentByUrl("http://music.163.com/discover/playlist");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPlayListAll() {
        WangyiyunCrawl crawl = new WangyiyunCrawl();
        List<PlayList> playList = crawl.getPlayList("http://music.163.com/discover/playlist/?cat=%E6%97%A5%E8%AF%AD");
        playList.stream().forEach(playList1 -> System.out.println(playList1));
    }

    // 测试网易云歌单里的所有歌
    @Test
    public void testGetWYYAllSong() throws Exception {
        WangyiyunCrawl crawl = new WangyiyunCrawl();
        List<Song> songList = crawl.getSongList("http://music.163.com/#/playlist?id=996670184");
        songList.stream().forEach(song -> System.out.println(song));
//        Jsoup.connect("http://music.163.com/playlist?id=996670184")
//                .header("Referer", "http://music.163.com/")
//                .header("Host", "music.163.com").get().select("ul")
//                .stream()//.map(w -> w.text() + "-->" + w.attr("href"))
//                .forEach(tr-> System.out.println(tr));
//        String data = "{\"id\":\"996670184\",\"offset\":0,\"total\":true,\"limit\":1000,\"n\":1000,\"csrf_token\":\"\"}";
//        Map<String, String> forms = WYYEncryptUtil.encrypt(data);
//        String url = "http://music.163.com/weapi/v3/playlist/detail";
//        String text = Requests.post(url).headers(headers).forms(forms).send().readToText();

//        System.out.println(text);

    }






    @Test
    public void testApi() {
//        String data = "{\"s\":\"442315717\",\"offset\":0,\";limit\":1,\"type\":\"1\"}";
//
//        Map<String, String> forms = WYYEncryptUtil.encrypt(data);
//        String url = "http://music.163.com/weapi/cloudsearch/get/web";

        String data = "{\"ids\":" + Arrays.asList("455345541") + ",\"br\":320000,\"csrf_token\":\"\"}";
        Map<String, String> forms = WYYEncryptUtil.encrypt(data);
        System.out.println(forms.values());
        String url = "http://music.163.com/weapi/song/enhance/player/url?csrf_token=";
        String text = Requests.post(url).headers(headers).forms(forms).send().readToText();
        System.out.println(text);
//        PlayListResponse playListResponse = JSON.parseObject(text, PlayListResponse.class);
//        System.out.println(text);
    }


    // 以下是虾米音乐的测试，懒得分了
    @Test
    public void testXMdecode() throws Exception {
        String code = "7h%1m2F29FE65mu%%4-E1be%5t22iF2F714_Ept35%%-a9%5EtF8.533%72273hDE556325E3p%.n45456637%_16EEb5%Eb1%2xe94%E9%5_3k5%%-df59163Fit%95%%56lFe155%cfE98aAma%2%E25E%.ay1EE51e26%8";
        String deURL = XiamiDecodeUtil.decode(code);
        String finallyURL = URLDecoder.decode(deURL, "utf-8");
        System.out.println(finallyURL.replace("^", "0"));
    }

    @Test
    public void testXMPlayListGet() {
        String url = "http://www.xiami.com/search/orinew?spm=a1z1s.3061701.6856305.6.9M9LaS&order=favorites&l=0";
        XiamiCrawl xm = new XiamiCrawl();
        xm.getPlayList(url).forEach(playList -> System.out.println(playList));

    }


    // 以下是QQ音乐的测试
    @Test
    public void TestQQmusic() {
        String jsonUrl = "https://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg?songmid=000UurX744Ro8M&tpl=yqq_song_detail&format=jsonp&callback=getOneSongInfoCallback&g_tk=5381&jsonpCallback=getOneSongInfoCallback&lo=ginUin0&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0";
        String jsonResult = Requests.get(jsonUrl).send().readToText().replace("getOneSongInfoCallback(", "").replace(")", "");
        System.out.println(jsonResult);
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        System.out.println(jsonObject.getJSONObject("url").entrySet().iterator().next().getValue());
    }

    @Test
    public void TestQQmusicPlayList() {
        String url = "https://c.y.qq.com/splcloud/fcgi-bin/fcg_get_diss_by_tag.fcg";
        QQCrawl qq = new QQCrawl();
        qq.getPlayList(url, 0).forEach(playList -> System.out.println(playList));
    }

}
