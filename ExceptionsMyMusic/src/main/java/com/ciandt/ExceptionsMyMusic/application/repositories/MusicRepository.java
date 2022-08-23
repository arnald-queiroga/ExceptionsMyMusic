package com.ciandt.ExceptionsMyMusic.application.repositories;

import com.ciandt.ExceptionsMyMusic.domain.entities.Artist;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, String> {

    List<Music> findMusicByName(String name);
    List<Music> findMusicByArtist(Artist artist);

//    @Query("SELECT m FROM Music m JOIN Artist a on a.id = m.artist WHERE lower(a.name) like lower(concat('%', :name,'%')) or lower(m.name) like lower(concat('%', :name,'%')) ORDER BY a.name, m.name ASC")
//    List<Music> findByArtistOrNameOfMusic(@Param("name") String name);

}
