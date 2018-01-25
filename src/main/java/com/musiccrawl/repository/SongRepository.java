package com.musiccrawl.repository;

import com.musiccrawl.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SongRepository extends CrudRepository<Song,Integer> {

    @Transactional
    @Modifying
    @Query(value="insert ignore into t_song(id,name,url,img_url,lrc_text,singer,plantform_code,type) values(?1,?2,?3,?4,?5,?6,?7,?8);",nativeQuery = true)
    void saveOne(String id,String name,String url,String imgUrl,String lrcText,String singer,Integer plantformCode,Integer type);

}
