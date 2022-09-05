package com.ciandt.ExceptionsMyMusic.application.controllers;

import com.ciandt.ExceptionsMyMusic.domain.dto.Data;
import com.ciandt.ExceptionsMyMusic.domain.dto.DataDTO;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.dto.TokenDataDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import com.ciandt.ExceptionsMyMusic.domain.services.PlaylistService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/playlists")
public class PlaylistController {
    private static final String AUTHORIZATION_NAME_HEADER = "name";
    private static final String AUTHORIZATION_TOKEN_HEADER = "token";
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistService playlistService;

    @Operation(summary = "Adicionar músicas a playlist", description = "Recebe uma lista de músicas e as adiciona à lista de reprodução")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Playlist Does Not Exist OR Existing Song in Playlist OR Payload Body Does Not Conform to Documentation"),
    })
    @PostMapping("/{playlistId}/musicas")
    public ResponseEntity<Playlist> findMusicandArtistByName(@PathVariable(value = "playlistId") String playlistId,
                                                             @RequestBody DataDTO dataDTO,
                                                             @RequestHeader(AUTHORIZATION_NAME_HEADER) String userName,
                                                             @RequestHeader(AUTHORIZATION_TOKEN_HEADER) String token) {
        TokenDataDTO tokenDataDTO = new TokenDataDTO(new Data(userName, token));
        MusicDTO musicDTO = dataDTO.getData().get(0);
        playlistService.addMusicToPlaylist(playlistId, musicDTO, tokenDataDTO);

        LOGGER.info("Operation performed successfully!");
        return new ResponseEntity<Playlist>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    @Operation(summary = "Remove music from playlist", description = "Receive a list of songs and add them to the playlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 400, message = "Playlist Does Not Exist OR Existing Song in Playlist OR Payload Body Does Not Conform to Documentation"),
    })
    public ResponseEntity<String> removeMusicFromPlaylist(@PathVariable(value = "playlistId") String playlistId,
                                                          @PathVariable(value = "musicaId") String musicaId,
                                                          @RequestHeader(AUTHORIZATION_NAME_HEADER) String userName,
                                                          @RequestHeader(AUTHORIZATION_TOKEN_HEADER) String token
    ) {
        TokenDataDTO tokenDataDTO = new TokenDataDTO(new Data(userName, token));
        playlistService.removeMusicToPlaylist(playlistId, musicaId, tokenDataDTO);

        return ResponseEntity.ok().body("Song successfully deleted!");
    }
}