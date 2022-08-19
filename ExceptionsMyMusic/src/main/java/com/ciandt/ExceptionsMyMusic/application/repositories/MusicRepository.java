package com.ciandt.ExceptionsMyMusic.application.repositories;

import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, String> {
}
