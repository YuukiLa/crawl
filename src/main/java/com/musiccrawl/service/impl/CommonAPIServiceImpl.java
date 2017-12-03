package com.musiccrawl.service.impl;

import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.crawl.qq.QQCrawl;
import com.musiccrawl.crawl.wangyiyun.WangyiyunCrawl;
import com.musiccrawl.crawl.xiami.XiamiCrawl;
import com.musiccrawl.entity.PlayList;
import com.musiccrawl.service.CommonAPIService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/23.
 */
@Service
public class CommonAPIServiceImpl implements CommonAPIService{

    /**
     * 根据code返回相应的歌单
     * @param code 1 网易云 ，2 虾米 ， 3 QQ音乐
     * @return
     */
    @Override
    public List<PlayList> getPlayList(int code) {
        switch (code){
            case WYY_CODE:
                return getWYYPlayList();
            case XM_CODE:
                return getXMPlayList();
            case QQ_CODE:
                return getQQPlayList();
            default:
                return null;
        }
    }
    // 获取所有的歌单
    @Override
    public List<PlayList> getAllPlayList() {
        List<PlayList> allList = new ArrayList<>();
        allList.addAll(getWYYPlayList());
        allList.addAll(getXMPlayList());
        allList.addAll(getQQPlayList());
        return allList;
    }


    private List<PlayList> getWYYPlayList(){
       return new WangyiyunCrawl().getPlayList(ICrawl.WYY_PLAYLIST_BASEUTL);
    }
    private List<PlayList> getXMPlayList(){
        return new XiamiCrawl().getPlayList(ICrawl.XM_PLAYLIST_BASEURL);
    }
    private List<PlayList> getQQPlayList(){
        return new QQCrawl().getPlayList(ICrawl.QQ_PLAYLIST_BASEURL,0);
    }
}
