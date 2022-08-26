package com.ciandt.ExceptionsMyMusic.application.controllers;

import com.ciandt.ExceptionsMyMusic.domain.dto.DataDTO;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import com.ciandt.ExceptionsMyMusic.domain.services.PlaylistService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @Operation(summary = "Adicionar músicas a playlist", description = "Recebe uma lista de músicas e as adiciona à lista de reprodução")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Playlist Does Not Exist OR Existing Song in Playlist OR Payload Body Does Not Conform to Documentation"),
    })
    @PostMapping("/{playlistId}/musicas")
    public ResponseEntity<Playlist> findMusicandArtistByName(@PathVariable(value = "playlistId") String playlistId, @RequestBody DataDTO dataDTO) {
        MusicDTO musicDTO = dataDTO.getData().get(0);
        playlistService.addMusicToPlaylist(playlistId, musicDTO);
        return new ResponseEntity<Playlist>(HttpStatus.CREATED);
    }
}