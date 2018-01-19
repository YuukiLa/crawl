package com.musiccrawl.crawl.xiami;

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

    public List<Song> getSongs(String url){
        List<Song> songs = new ArrayList<>();
        try {
            String result = getContentByUrl(url);
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            Elements elements = document.select(".chapter .quote_song_list li");
            System.out.println(elements.size());
            for (Element e: elements) {
                Song song = new Song();
                Element select = e.select(".Qsong_item .s_info").first();
                Map<String ,String > mMap = getSongMsg(select.getElementsByClass("song_name").first().child(0).absUrl("href"));
                try {
                    song.setUrl(mMap.get("songUrl"));
                }catch (NullPointerException ee){
                    continue;
                }
                song.setImgUrl(null);
                song.setName(select.getElementsByClass("song_name").first().child(0).attr("title"));
                song.setImgUrl(mMap.get("imgUrl"));
                songs.add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

    private static final String SONG_MSG_URL="http://www.xiami.com/widget/xml-single/uid/0/sid/";
    // 获取歌曲链接,图片链接
    private Map<String,String > getSongMsg(String url) throws Exception {
        Map<String ,String > mMap = new HashMap<>();
        String result = getContentByUrl(url);
        Document document = Jsoup.parse(result);
        document.setBaseUri(url);
        Element element = document.select(".cd2play a").first();
        String songId = element.attr("onclick");
        songId = songId.split("'")[1];
        String requestUrl = SONG_MSG_URL+songId;
        String read = Requests.get(requestUrl).send().readToText();
        org.dom4j.Document doc;
        try{
            doc = DocumentHelper.parseText(read);
            org.dom4j.Element rootElement = doc.getRootElement();
            Node track = rootElement.selectSingleNode("track");
            Node node = track.selectSingleNode("album_cover");
            String imgUrl = node.getText();
            mMap.put("imgUrl",imgUrl);
            node = track.selectSingleNode("location");
            String songCode = node.getText();
            String deURL = XiamiDecodeUtil.decode(songCode);
            String finallyURL = URLDecoder.decode(deURL, "utf-8");
            String songUrl = finallyURL.replace("^", "0");
            mMap.put("songUrl",songUrl);
            return mMap;
        }catch (DocumentException e){
            return null;
        }
    }

}
