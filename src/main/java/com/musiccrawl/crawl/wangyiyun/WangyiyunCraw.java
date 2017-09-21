package com.musiccrawl.crawl.wangyiyun;

import com.musiccrawl.entity.PlayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */
public class WangyiyunCraw extends DefaultCrawl {

    public List<PlayList> getPlayList(String url) {
        try {
            String result = getPlayListByUrl(url);
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            Elements elements = document.select(".m-cvrlst li");
            List<PlayList> list = new ArrayList<>();
            for (Element e: elements) {
                PlayList play = new PlayList();
                Element select = e.select(".u-cover .j-flag").first();
                play.setImgUrl(select.attr("src"));
                select = e.select(".u-cover a").first();
                play.setTitle(select.attr("title"));
                play.setUrl(select.absUrl("href"));
                select = e.select(".u-cover .bottom .nb").first();
                play.setCount(select.text());
                list.add(play);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
