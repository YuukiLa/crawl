package com.musiccrawl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 歌曲实体
 * Created by Administrator on 2017/12/3.
 */
@Entity
public class Song {

    @Id
    @GeneratedValue
    private Integer mid;
    @Column(unique = true,length = 40)
    private String id;     // 歌曲id  mid什么的
    @Column(length = 50)
    private String name;  // 歌曲名称
    @Column(length = 150)
    private String url;   // 歌曲链接
    @Column(length = 150)
    private String imgUrl;  // 图片地址 如果有的话
    private String lrcText;  // 歌词地址 如果有的话
    @Column(length = 50)
    private String singer; // 歌手
    private Integer plantformCode; // 平台编号
    private Integer type;   // 类型

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPlantformCode() {
        return plantformCode;
    }

    public void setPlantformCode(Integer plantformCode) {
        this.plantformCode = plantformCode;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

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

    public String getLrcText() {
        return lrcText;
    }

    public void setLrcText(String lrcText) {
        this.lrcText = lrcText;
    }

    @Override
    public String toString() {
        return "Song{" +
                "mid=" + mid +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", lrcText='" + lrcText + '\'' +
                ", singer='" + singer + '\'' +
                ", plantformCode=" + plantformCode +
                ", type=" + type +
                '}';
    }
}
