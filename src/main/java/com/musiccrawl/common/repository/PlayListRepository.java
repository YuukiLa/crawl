package com.musiccrawl.common.repository;

import com.musiccrawl.common.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList,String >,JpaSpecificationExecutor<PlayList> {
    @Modifying
    @Query(value = "SELECT * FROM t_playlist LIMIT ?1,25",nativeQuery = true)
    List<PlayList> findByPage(int page);

}
