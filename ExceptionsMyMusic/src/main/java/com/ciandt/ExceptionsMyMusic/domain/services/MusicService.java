package com.ciandt.ExceptionsMyMusic.domain.services;

import com.ciandt.ExceptionsMyMusic.application.repositories.MusicRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.NoContentException;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MusicService.class);
    @Autowired
    private MusicRepository musicRepository;

    @Transactional(readOnly = true)
    public List<MusicDTO> findByArtistOrMusic(String nome) {

        if (nome.length() <= 2) {
            LOGGER.error("O filtro tem menos que 3 carácteres, portanto busca inválida");
            throw new ResourceNotFoundException("O filtro deve ter 3 ou mais caracteres");
        }
        LOGGER.info("Busca realizada com sucesso, pois filtro possui mais de 2 caracteres");
        List<Music> list = musicRepository.findByArtistOrNameOfMusic(nome);

        if (list.isEmpty()) {
            LOGGER.error("O filtro não retornou dados para pesquisa realizada!");
            throw new NoContentException("Dados não encontrados");
        }
        LOGGER.info("Busca realizada com sucesso, pois dados encontrados na base");
        List<MusicDTO> listDTO = list.stream().map(music -> new MusicDTO(music)).collect(Collectors.toList());

        LOGGER.info("Dados retornados com sucesso!");
        return listDTO;
    }
}