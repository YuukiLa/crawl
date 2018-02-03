package com.musiccrawl.api.service.impl;

import com.musiccrawl.common.entity.PlayList;
import com.musiccrawl.common.entity.Song;
import com.musiccrawl.common.repository.PlayListRepository;
import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.crawl.qq.QQCrawl;
import com.musiccrawl.crawl.wangyiyun.WangyiyunCrawl;
import com.musiccrawl.crawl.xiami.XiamiCrawl;
import com.musiccrawl.api.service.CommonAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017/10/23.
 */
@Service
@Transactional
public class CommonAPIServiceImpl implements CommonAPIService {

    @Autowired
    private PlayListRepository playListRepository;


    @Override
    public Map<String, Object> getPlayList(int next) {
        Map<String,Object> map = new HashMap<>();
        int i = new Random().nextInt(52000);
//        System.out.println(i);
        List<PlayList> lists = playListRepository.findByPage(i);
        map.put("playList",lists);
        map.put("next",++next);
        return map;
    }

    @Override
    public List<Song> getSongList(String url) {
       if(url.contains("163")){
            return new WangyiyunCrawl().getSongList(url);
       }else if(url.contains("xiami")){
           try {
               return new XiamiCrawl().getSongs(url);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }else if(url.contains("qq")) {
           return new QQCrawl().getSongs(url);
       }
        return null;
    }


    private Map<String, Object> getWYYPlayList(String next) {
        Pageable pageable= new PageRequest(1,1);
        playListRepository.findAll();
        return new WangyiyunCrawl().getPlayList(next);
    }

    private Map<String, Object> getXMPlayList(String nextUrl) {
        return new XiamiCrawl().getPlayList(nextUrl);
    }

    private Map<String, Object> getQQPlayList(String nextUrl) {
        return new QQCrawl().getPlayList(ICrawl.QQ_PLAYLIST_BASEURL, Integer.parseInt(nextUrl));
    }
}
