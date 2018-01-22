package com.musiccrawl.service.impl;

import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.crawl.qq.QQCrawl;
import com.musiccrawl.crawl.wangyiyun.WangyiyunCrawl;
import com.musiccrawl.crawl.xiami.XiamiCrawl;
import com.musiccrawl.entity.PlayList;
import com.musiccrawl.service.CommonAPIService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/23.
 */
@Service
public class CommonAPIServiceImpl implements CommonAPIService {

    /**
     * 根据code返回相应的歌单
     *
     * @param code    1 网易云 ，2 虾米 ， 3 QQ音乐
     * @param nextUrl 下一页地址
     * @return
     */
    @Override
    public Map<String, Object> getPlayList(int code, String nextUrl) {
        switch (code) {
            case WYY_CODE:
                nextUrl = nextUrl.equals("") ? ICrawl.WYY_PLAYLIST_BASEUTL : nextUrl;
                return getWYYPlayList(nextUrl);
            case XM_CODE:
                nextUrl = nextUrl.equals("") ? ICrawl.XM_PLAYLIST_BASEURL : nextUrl;
                return getXMPlayList(nextUrl);
            case QQ_CODE:
                nextUrl = nextUrl.equals("") ? 0 + "" : nextUrl;
                return getQQPlayList(nextUrl);
            default:
                return null;
        }
    }

    // 获取所有的歌单
    @Override
    public List<PlayList> getAllPlayList() {
        List<PlayList> allList = new ArrayList<>();
//        allList.addAll(getWYYPlayList());
//        allList.addAll(getXMPlayList());
//        allList.addAll(getQQPlayList());
        return allList;
    }

    @Override
    public Map<String, Object> getSongList(int code, String url) {
        Map<String ,Object> songList = new HashMap<>();
        switch (code) {
            case WYY_CODE:
                songList.put("songs",new WangyiyunCrawl().getSongList(url));
                break;
            case XM_CODE:
                try {
                    songList.put("songs",new XiamiCrawl().getSongs(url));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case QQ_CODE:
                songList.put("songs",new QQCrawl().getSongs(url));
                break;
            default:
                break;
        }
        return songList;
    }


    private Map<String, Object> getWYYPlayList(String nextUrl) {
        return new WangyiyunCrawl().getPlayList(nextUrl);
    }

    private Map<String, Object> getXMPlayList(String nextUrl) {
        return new XiamiCrawl().getPlayList(nextUrl);
    }

    private Map<String, Object> getQQPlayList(String nextUrl) {
        return new QQCrawl().getPlayList(ICrawl.QQ_PLAYLIST_BASEURL, Integer.parseInt(nextUrl));
    }
}
