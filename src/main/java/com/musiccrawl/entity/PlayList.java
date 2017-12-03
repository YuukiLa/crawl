package com.musiccrawl.entity;

/**
 * 歌单实体
 * Created by Administrator on 2017/9/16.
 */
public class PlayList {

    private Integer id;     //id  目前还没有什么用
    private String title;  //    歌单标题
    private String imgUrl; //    歌单图片地址
    private String url;    //    歌单地址
    private String count;  //    歌单播放次数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", url='" + url + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
