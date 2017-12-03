package com.musiccrawl.crawl.xiami;

import com.musiccrawl.crawl.DefaultCrawl;
import com.musiccrawl.entity.PlayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 虾米音乐歌单爬取
 * Created by Administrator on 2017/10/19.
 */
public class XiamiCrawl extends DefaultCrawl{

    public List<PlayList> getPlayList(String url){
        try {
            String result = getContentByUrl(url);
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            Elements elements = document.select(".block_list li");
            List<PlayList> list = new ArrayList<>();
            System.out.println("size==========>"+elements.size());
            for (Element e: elements) {
                PlayList play = new PlayList();
                Element select = e.select(".block_items .block_cover").first();
                play.setImgUrl(select.select("a img").attr("src"));
                play.setTitle(select.select("a").attr("title"));
                play.setUrl(select.select("a").attr("href"));
                play.setCount(e.select(".block_items .collect_action").first().text());
                list.add(play);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }

}
