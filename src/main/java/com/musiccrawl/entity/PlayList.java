package com.musiccrawl.entity;

import javax.persistence.*;

/**
 * 歌单实体
 * Created by Administrator on 2017/9/16.
 */
@Entity
@Table(name="t_playlist")
public class PlayList {

    @Id
    @GeneratedValue
    private Integer mid;
    @Column(unique = true,length = 20)
    private String id;     //id
    @Column(length = 60)
    private String title;  //    歌单标题
    @Column(length = 150)
    private String imgUrl; //    歌单图片地址
    @Column(length = 150)
    private String url;    //    歌单地址
    @Column(length = 20)
    private String count;  //    歌单播放次数
    private Integer platformCode; // 平台代码
    private Integer type;   // 歌单风格

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setPlatformCode(Integer platformCode) {
        this.platformCode = platformCode;
    }

    public int getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(int platformCode) {
        this.platformCode = platformCode;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                "mid=" + mid +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", url='" + url + '\'' +
                ", count='" + count + '\'' +
                ", platformCode=" + platformCode +
                ", type=" + type +
                '}';
    }
}
