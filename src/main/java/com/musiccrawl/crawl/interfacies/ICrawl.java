package com.musiccrawl.crawl.interfacies;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */
public interface ICrawl {

    public static final String WYY_PLAYLIST_BASEURL ="http://music.163.com/discover/playlist";
    public static final String XM_PLAYLIST_BASEURL="http://www.xiami.com/search/orinew?spm=a1z1s.3061701.6856305.6.9M9LaS&order=favorites&l=0";
    public static final String QQ_PLAYLIST_BASEURL="https://c.y.qq.com/splcloud/fcgi-bin/fcg_get_diss_by_tag.fcg";


    public String getContentByUrl(String url) throws Exception;


}
