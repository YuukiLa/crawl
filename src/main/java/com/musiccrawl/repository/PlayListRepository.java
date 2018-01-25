package com.musiccrawl.repository;

import com.musiccrawl.entity.PlayList;
import com.musiccrawl.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList,String >,JpaSpecificationExecutor<PlayList> {
}
