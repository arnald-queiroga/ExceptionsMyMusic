package com.ciandt.ExceptionsMyMusic.application.controllers;

import com.ciandt.ExceptionsMyMusic.domain.dto.DataDTO;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import com.ciandt.ExceptionsMyMusic.domain.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/{playlistId}/musicas")
    public ResponseEntity<Playlist> findMusicandArtistByName(@PathVariable (value = "playlistId") String playlistId, @RequestBody DataDTO dataDTO){
        MusicDTO musicDTO = dataDTO.getData().get(0);
        playlistService.addMusicToPlaylist(playlistId, musicDTO);
        return new ResponseEntity<Playlist>(HttpStatus.CREATED);
    }
}