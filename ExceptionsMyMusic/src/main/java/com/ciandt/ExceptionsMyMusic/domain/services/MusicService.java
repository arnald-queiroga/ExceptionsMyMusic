package com.ciandt.ExceptionsMyMusic.domain.services;

import com.ciandt.ExceptionsMyMusic.application.repositories.MusicRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MusicService {

    @Autowired
    private MusicRepository repository;

    @Transactional(readOnly = true)
    public List<MusicDTO> findAll(){
        List<Music> list = repository.findAll();
        return list.stream().map(x -> new MusicDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MusicDTO findMusic(String name){
        Optional<Music> obj = repository.findByName(name);
        Music entity = obj.get();
        return new MusicDTO(entity);
    }

//    @Transactional(readOnly = true)
//    public List<Music> findMusic(String name){
//        List<Music> musicasEncontradas = repository.findByNameMusic(name);
//        return musicasEncontradas;
//    }

}
