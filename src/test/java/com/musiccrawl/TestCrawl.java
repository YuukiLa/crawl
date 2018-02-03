package com.musiccrawl;

import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.crawl.qq.QQCrawl;
import com.musiccrawl.crawl.wangyiyun.WangyiyunCrawl;
import com.musiccrawl.crawl.xiami.XiamiCrawl;
import com.musiccrawl.common.entity.PlayList;
import com.musiccrawl.common.entity.Song;
import com.musiccrawl.common.myexception.FailedCrawlResultException;
import com.musiccrawl.util.XiamiDecodeUtil;
import net.dongliu.requests.Requests;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        List<PlayList> playList = (List<PlayList>) crawl.getPlayList("http://music.163.com/discover/playlist/?cat=%E6%97%A5%E8%AF%AD");
        playList.stream().forEach(playList1 -> System.out.println(playList1));
    }

    // 测试网易云歌单里的所有歌
    @Test
    public void testGetWYYAllSong() throws Exception {
        WangyiyunCrawl crawl = new WangyiyunCrawl();
        List<Song> songList = crawl.getSongList("http://music.163.com/playlist?id=988454757");
        System.out.println(songList.size()+"aaaaaaaaaaaaaaa");
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
    public void testGetNestLyric(){
//        String lyric = new WangyiyunCrawl().getLyric("509781260");
//        System.out.println(lyric);
    }

    @Test
    public void testSearchWYY(){
        new WangyiyunCrawl().serachSong("枫");
    }



    @Test
    public void testApi() {
//        String data = "{\"s\":\"442315717\",\"offset\":0,\";limit\":1,\"type\":\"1\"}";
//
//        Map<String, String> forms = WYYEncryptUtil.encrypt(data);
//        String url = "http://music.163.com/weapi/cloudsearch/get/web";

//        String data = "{\"ids\":" + Arrays.asList("504624714") + ",\"br\":320000,\"csrf_token\":\"\"}";
//        Map<String, String> forms = WYYEncryptUtil.encrypt(data);
//        String url = "http://music.163.com/weapi/song/enhance/player/url?csrf_token=";
//        String text = Requests.post(url).headers(headers).forms(forms).send().readToText();
//        System.out.println(text);
            new WangyiyunCrawl().getSongUrl("504624714");
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
        List<PlayList> playlist = (List<PlayList>) xm.getPlayList(url).get("playList");
        playlist.forEach(playList -> System.out.println(playList));

    }
    // 虾米音乐歌单内容爬取
    @Test
    public void testXMplayListContent() throws Exception {
//        String url = "http://www.xiami.com/collect/359585115";
        XiamiCrawl xm = new XiamiCrawl();
        List<Song> songs = xm.getSongs("356505475", 2);
//        List<Song> songs = xm.getSongs(url);
        songs.stream().forEach(song -> System.out.println(song));
       // xm.getSongMsg("http://www.xiami.com/song/mQ7nE683e2f");
    }
    @Test
    public void testXMinfo() throws Exception {
        Map<String ,Object > header = new HashMap<>();
        header.put("Host","api.xiami.com");
        header.put("Origin","http://m.xiami.com/");
        header.put("Referer","http://m.xiami.com/");
        header.put("Upgrade-Insecure-Requests","1");
        header.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        header.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        header.put("Accept-Encoding","gzip, deflate");
        header.put("Accept-Language","zh-CN,zh;q=0.9");
        header.put("Cache-Control","max-age=0");
        header.put("Connection","keep-alive");

        String url = "http://api.xiami.com/web?v=2.0&app_key=1&id=362468180&callback=jsonp122&r=collect/detail";

        Map<String ,Object > cookie = new HashMap<>();
        cookie.put("gid","15102075309988");
        cookie.put("_unsign_token","76ef702191632b91563378b21378ebc7");
        cookie.put("bdshare_firstime","1514961226048");
        cookie.put("cna","");
        cookie.put("_m_h5_tk","0c28e97a6662c95b302b0a3fb94177a7_1516102596366");
        cookie.put("_m_h5_tk_enc","b7ef470c217187b93f49e1221b0ec347");
        cookie.put("login_method","sinalogin");
        cookie.put("recent_tags","%E5%8D%8E%E8%AF%AD+%E9%9F%A9%E8%AF%AD+%E6%80%80%E6%97%A7+%E7%BB%8F%E5%85%B8+");
        cookie.put("_xiamitoken","70aa2b9ebc293f1912a0410d446793a5");
        cookie.put("user_from",1);
        cookie.put("t_sign_auth",1);
        cookie.put("XMPLAYER_url","/song/playlist/id/362475036/type/3");
        cookie.put("XMPLAYER_addSongsToggler",0);
        cookie.put("__guestplay","MTgwMTM3NTk5NCwxOzE3NzA0MTc2MzYsMjsxNzcwNzQ3OTQ5LDI7MTc2OTkzMzcwNywzOzE3Njk5MTU3NDMsMzsxNzk2OTA5OTE1LDE7MTc3NjMwMzg3NiwyOzE3NzQ1NjQwNTQsMjsxNzkyNjEwMzM1LDI7MzczOTY1LDI7MTc3MDY4NTczMiwx");
        cookie.put("XMPLAYER_isOpen",0);
        cookie.put("isg","Av7-BdXFKMwfcXzQD-KUtNqkTxSAl8KXaDIBWqgHasE8S54lEM8SySQptSF8");
        String s = Requests.get("http://img.xiami.net/lyric/61/1771070161_1472632864_7747.lrc").send().readToText();
        System.out.println(s);
    }


    // 以下是QQ音乐的测试
    @Test
    public void TestQQmusic() {
//        String jsonUrl = "https://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg?songmid=000UurX744Ro8M&tpl=yqq_song_detail&format=jsonp&callback=getOneSongInfoCallback&g_tk=5381&jsonpCallback=getOneSongInfoCallback&lo=ginUin0&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0";
//        String jsonResult = Requests.get(jsonUrl).send().readToText().replace("getOneSongInfoCallback(", "").replace(")", "");
//        System.out.println(jsonResult);
//        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
//        System.out.println(jsonObject.getJSONObject("url").entrySet().iterator().next().getValue());
        QQCrawl qq = new QQCrawl();
        System.out.println(qq.getSongUrl("004RUiXu49ufy1"));
    }

    @Test
    public void TestQQmusicPlayList() {
        String url = "https://c.y.qq.com/splcloud/fcgi-bin/fcg_get_diss_by_tag.fcg";
        QQCrawl qq = new QQCrawl();
        try{
            List list = (List) qq.getPlayList(ICrawl.QQ_PLAYLIST_BASEURL,1,167, 2).get("playList");
        }catch (FailedCrawlResultException e){
            System.out.println("sssssssssssss");
        }

        //list .forEach(playList -> System.out.println(playList));
    }


    @Test
    public void testQQplayListContnet(){
        String url = "https://y.qq.com/n/yqq/playlist/3607386766.html";
        QQCrawl qq = new QQCrawl();
        List<Song> songs = qq.getSongs(url);
        songs.forEach(song -> System.out.println(song));
    }

    @Test
    public void testGetLyric(){
        //new QQCrawl().getLyric("201385986");
    }

    // 一些无聊的测试
    @Test
    public void testRandom(){
        for (int i = 0; i <20 ; i++) {
            int j = new Random().nextInt(52000);
            System.out.println(j);
        }

    }
}
