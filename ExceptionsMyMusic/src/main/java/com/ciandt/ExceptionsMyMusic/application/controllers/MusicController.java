package com.ciandt.ExceptionsMyMusic.application.controllers;

import com.ciandt.ExceptionsMyMusic.domain.dto.DataDTO;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.services.MusicService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MusicController {
    @Autowired
    private MusicService musicService;

    @Operation(summary = "Buscar músicas por nome do artista ou nome da música", description = "Pesquise pelo nome do artista ou nome da música. A pesquisa de filtro não diferencia maiúsculas de minúsculas. O filtro deve ter pelo menos 3 caracteres.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 204, message = "No results"),
            @ApiResponse(code = 400, message = "Not enough characters")
    })
    @GetMapping(value = "/musicas")
    public ResponseEntity<?> findMusicandArtistByName(@RequestParam(value = "filtro") String name) {
        List<MusicDTO> dtoMusic = musicService.findByArtistOrMusic(name);
        return ResponseEntity.ok().body(new DataDTO(dtoMusic));
    }
}
