package com.ciandt.ExceptionsMyMusic.application.controllers;

import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/{playlistId}/musicas")
    public void findMusicandArtistByName(@PathVariable (value = "playlistId") String playlistId, @RequestBody MusicDTO musicDTO){
        playlistService.addMusicToPlaylist(playlistId, musicDTO);
    }
}