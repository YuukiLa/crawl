package com.musiccrawl.service;

import com.musiccrawl.entity.PlayList;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/23.
 */
public interface CommonAPIService {

    //常量定义，区分请求的是哪个网站的歌单
    final int WYY_CODE = 1;
    final int XM_CODE = 2;
    final int QQ_CODE = 3;

    //
    Map<String,Object> getPlayList(int code,String nextUrl);
    // 获取所有的歌单
    List<PlayList>  getAllPlayList();
}
