package com.musiccrawl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 歌曲实体
 * Created by Administrator on 2017/12/3.
 */
@Entity
public class Song {

    @Id
    private String id;     // 歌曲id  mid什么的
    private String name;  // 歌曲名称
    private String url;   // 歌曲链接
    private String imgUrl;  // 图片地址 如果有的话
    private String lrcUrl;  // 歌词地址 如果有的话
    private String singer; // 歌手

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLrcUrl() {
        return lrcUrl;
    }

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", lrcUrl='" + lrcUrl + '\'' +
                '}';
    }
}
