package com.ciandt.ExceptionsMyMusic.domain.services;

import com.ciandt.ExceptionsMyMusic.application.repositories.MusicRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.NoContentException;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    @Transactional(readOnly = true)
    public List<MusicDTO> findByArtistOrMusic(String nome) {

        if (nome.length() <= 2){
            throw new ResourceNotFoundException("O filtro deve ter 3 ou mais caracteres");
        }

        List<Music> list = musicRepository.findByArtistOrNameOfMusic(nome);

        if (list.isEmpty()){
            throw new NoContentException("Dados não encontrados");
        }
        List<MusicDTO> listDTO = list.stream().map(music -> new MusicDTO(music)).collect(Collectors.toList());

        return listDTO;
    }

}
