package com.musiccrawl.controller;

import com.musiccrawl.entity.PlayList;
import com.musiccrawl.model.ResponseModel;
import com.musiccrawl.service.CommonAPIService;
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
    public ResponseModel getPlayList(@RequestParam("code") int code,@RequestParam(value = "nextUrl",required=false)String nextUrl){
        logger.debug(code+"...nextUrl"+nextUrl);
        nextUrl = nextUrl==null ? "":nextUrl;
        Map<String,Object> playList = commonAPIService.getPlayList(code,nextUrl);
        return new ResponseModel<Map<String,Object>>(ResponseModel.SUCCESS_CODE,playList);
    }
    // 获取所有的歌单
    @GetMapping("/getallplaylist")
    public ResponseModel getPlayList(){
        return new ResponseModel<List<PlayList>> (ResponseModel.SUCCESS_CODE,commonAPIService.getAllPlayList());
    }


    // 根据url 获取歌单歌曲
    @GetMapping("/getSongs")
    public ResponseModel getSongList(@RequestParam String url,@RequestParam(value = "code",required = false) int code){
       return new ResponseModel<Map<String,Object>> (ResponseModel.SUCCESS_CODE,commonAPIService.getSongList(code,url));
    }



    @PostMapping("/test")
    public String test(@RequestBody String name,@RequestParam(value = "psw",required = false)String psw){
        logger.debug(name+"11111111111"+psw);
        return "ssssssss";
    }


}
