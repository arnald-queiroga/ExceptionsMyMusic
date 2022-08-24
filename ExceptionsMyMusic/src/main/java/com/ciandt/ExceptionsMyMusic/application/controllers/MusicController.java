package com.ciandt.ExceptionsMyMusic.application.controllers;

import com.ciandt.ExceptionsMyMusic.domain.dto.DataDTO;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping(value = "/musicas")
    public ResponseEntity<?> findMusicandArtistByName(@RequestParam (value = "name") String name){
        List<MusicDTO> dtoMusic = musicService.findByArtistOrMusic(name);
        return ResponseEntity.ok().body(new DataDTO(dtoMusic));
    }
}
