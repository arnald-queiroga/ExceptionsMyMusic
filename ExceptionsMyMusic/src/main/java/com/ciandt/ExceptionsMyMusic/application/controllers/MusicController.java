package com.ciandt.ExceptionsMyMusic.application.controllers;

import com.ciandt.ExceptionsMyMusic.application.repositories.MusicRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/musics")
public class MusicController {

    @Autowired
    private MusicService service;

    @Autowired
    private MusicRepository repository;

    @GetMapping
    public ResponseEntity<List<MusicDTO>> findAll(){
        List<MusicDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "findMusicByName")
    @ResponseBody
    public ResponseEntity<MusicDTO> findByName(@RequestParam(name = "name") String name){
        MusicDTO dto = service.findMusic(name);
        return ResponseEntity.ok().body(dto);
    }

}
