package com.ciandt.ExceptionsMyMusic.application.repositories;

import com.ciandt.ExceptionsMyMusic.domain.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, String> {
}
