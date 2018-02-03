package com.musiccrawl.api.controller;

import com.musiccrawl.common.entity.Song;
import com.musiccrawl.common.repository.PlayListRepository;
import com.musiccrawl.common.entity.PlayList;
import com.musiccrawl.common.model.ResponseModel;
import com.musiccrawl.api.service.CommonAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 通用api接口
 * Created by Administrator on 2017/10/23.
 */
@RestController
@RequestMapping("/api")
public class CommonAPIController {

    @Autowired
    private CommonAPIService commonAPIService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    // 根据code 获取歌单
    @GetMapping("/getplaylist")
    public ResponseModel getPlayList(@RequestParam(value = "type",required = false) int type,@RequestParam(value = "next",required=false)int next){
        logger.debug("...nextUrl"+next);
        Map<String,Object> playList = commonAPIService.getPlayList(next);
        return new ResponseModel<Map<String,Object>>(ResponseModel.SUCCESS_CODE,playList);
    }

    // 根据url 获取歌单歌曲
    @GetMapping("/getSongs")
    public ResponseModel getSongList(@RequestParam String url,@RequestParam(value = "code",required = false) int code){
       return new ResponseModel<List<Song>> (ResponseModel.SUCCESS_CODE,commonAPIService.getSongList(url));
    }


}
