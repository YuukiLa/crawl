package com.musiccrawl.crawl.xiami;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiccrawl.crawl.DefaultCrawl;
import com.musiccrawl.entity.PlayList;
import com.musiccrawl.entity.Song;
import com.musiccrawl.myexception.FailedCrawlResultException;
import com.musiccrawl.util.XiamiDecodeUtil;
import net.dongliu.requests.Requests;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 虾米音乐歌单爬取
 * Created by Administrator on 2017/10/19.
 */
public class XiamiCrawl extends DefaultCrawl{

    public Map<String,Object> getPlayList(String url) throws FailedCrawlResultException{
        return getPlayList(url,1);
    }

    public Map<String,Object> getPlayList(String url,int type) throws FailedCrawlResultException{
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String result = getContentByUrl(url);
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            Elements elements = document.select(".block_list li");
            List<PlayList> list = new ArrayList<>();
            for (Element e: elements) {
                PlayList play = new PlayList();
                Element select = e.select(".block_items .block_cover").first();
                play.setImgUrl(select.select("a img").attr("src"));
                play.setTitle(select.select("a").attr("title"));
                play.setUrl(select.select("a").attr("href"));
                play.setCount(e.select(".block_items .collect_action").first().text());
                play.setPlatformCode(2);
                play.setType(type);
                play.setId(play.getUrl().split("collect/")[1]);
                list.add(play);
            }
            resultMap.put("playList",list);
            // 获取下一页
            elements = document.select(".all_page .p_redirect_l");
            String nextUrl = elements.first().absUrl("href");
            resultMap.put("nextUrl",nextUrl);
            return resultMap;

        } catch (Exception e) {
            e.printStackTrace();
             throw new FailedCrawlResultException("未抓取到数据");
        }
    }

    public List<Song> getSongs(String url) throws InterruptedException {
        return getSongs(url,1);
    }
    private static final String SONG_MSG_URL="http://api.xiami.com/web?v=2.0&app_key=1&id=%s&callback=jsonp122&r=collect/detail";
    private static final String SONG_DETIL = "http://www.xiami.com/song/playlist/id/%s/object_name/default/object_id/0/cat/json";
    public List<Song> getSongs(String id,int type){
        List<Song> songs = new ArrayList<>();
//        try {
//            String result = getContentByUrl(url);
//            Document document = Jsoup.parse(result);
//            document.setBaseUri(url);
//            Elements elements = document.select(".chapter .quote_song_list li");
////            System.out.println(result);
//            for (Element e: elements) {
//                Song song = new Song();
//                Element select = e.select(".Qsong_item .s_info").first();
//                Element element = select.getElementsByClass("song_name").first();
//                Map<String ,String > mMap = getSongMsg(element.child(0).absUrl("href"));
//                try {
//                    song.setUrl(mMap.get("songUrl"));
//                }catch (NullPointerException ee){
//                    continue;
//                }
//                song.setName(element.child(0).attr("title"));
//                song.setId(element.child(0).attr("href").split("/")[2]);
//                //String singer = element.children().size()==2?:element.child(1).text()
//                song.setSinger(element.child(1).text());
//                song.setPlantformCode(2);
//                song.setType(type);
//                song.setImgUrl(mMap.get("imgUrl"));
//                song.setLrcText(mMap.get("lyric"));
//                songs.add(song);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            String result = Requests.get(String.format(SONG_MSG_URL, id)).headers(getHeader()).cookies(getCookie()).send().readToText();
            result = result.replace("jsonp122(", "");
            result = result.substring(0, result.length() - 1);
            JSONObject object = JSONObject.parseObject(result);
            JSONArray array = object.getJSONObject("data").getJSONArray("songs");
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Song song = new Song();
                song.setId(obj.getInteger("song_id") + "");
                song.setName(obj.getString("song_name"));
                song.setImgUrl(obj.getString("album_logo"));
                song.setSinger(obj.getString("singers"));
                Map header = getHeader();
                header.put("Host", "www.xiami.com");
                String s = Requests.get(String.format(SONG_DETIL, song.getId())).headers(header).cookies(getCookie()).send().readToText();
                JSONObject o = JSONObject.parseObject(s);
                String lyric = o.getJSONObject("data").getJSONArray("trackList").getJSONObject(0).getString("lyric_url");
                if (!lyric.equals("")) {
                    song.setLrcText(Requests.get(lyric).send().readToText());
                }
                song.setPlantformCode(2);
                song.setType(type);
                song.setUrl(obj.getString("listen_file"));
                songs.add(song);
            }
        }catch (Exception e){

        }
        return songs;
    }


    // 获取歌曲链接,图片链接
    public Map<String,String > getSongMsg(String id) throws Exception {
        Map<String ,String > mMap = new HashMap<>();
        String result = Requests.get(String.format(SONG_MSG_URL,id)).headers(getHeader()).cookies(getCookie()).send().readToText();
        result = result.replace("jsonp122(","");
        result = result.substring(0,result.length()-1);
        System.out.println(String.format(SONG_MSG_URL,id));
        JSONObject object = JSONObject.parseObject(result);
        System.out.println(object.getString("request_id"));
        JSONArray array = object.getJSONObject("data").getJSONArray("songs");
        System.out.println(array.size());

        return null;
//        String result = getContentByUrl(url);
//        Document document = Jsoup.parse(result);
//        document.setBaseUri(url);
//        org.dom4j.Document doc;
//        try{
//            Element element = document.select(".cd2play a").first();
//            String songId = element.attr("onclick");
//            songId = songId.split("'")[1];
//            String requestUrl = SONG_MSG_URL+songId+"/object_name/default/object_id/0";
//            String read = Requests.get(requestUrl).headers(getHeader()).cookies(getCookie()).send().readToText();
//            System.out.println(requestUrl);
//            doc = DocumentHelper.parseText(read);
//            org.dom4j.Element rootElement = doc.getRootElement();
//            Node track = rootElement.selectSingleNode("trackList").selectSingleNode("track");
//            Node node = track.selectSingleNode("album_pic");
//            String imgUrl = node.getText();
//            mMap.put("imgUrl",imgUrl);
//            node = track.selectSingleNode("location");
//            String songCode = node.getText();
//            String deURL = XiamiDecodeUtil.decode(songCode);
//            String finallyURL = URLDecoder.decode(deURL, "utf-8");
//            String songUrl = finallyURL.replace("^", "0");
//            mMap.put("songUrl",songUrl);
//            Node lyric = track.selectSingleNode("lyric");
//            mMap.put("lyric",lyric.getText());
//            return mMap;
//        }catch (DocumentException e){
////            System.out.println(e.getMessage());
//            return null;
//        }catch (NullPointerException ee){
//            return null;
//        }
    }

    private Map<String,String > getHeader(){
        Map<String ,String > header = new HashMap<>();
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
        return header;
    }
    private Map<String,Object > getCookie(){
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
        return cookie;
    }

}
