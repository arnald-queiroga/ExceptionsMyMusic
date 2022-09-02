package com.ciandt.ExceptionsMyMusic.applications.controller;

import com.ciandt.ExceptionsMyMusic.application.controllers.MusicController;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Artist;
import com.ciandt.ExceptionsMyMusic.domain.services.MusicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MusicController.class)
public class MusicControllerTests {

    @MockBean
    private MusicService musicService;

    @InjectMocks
    private MusicController musicController;

    @Autowired
    private MockMvc mockMvc;

    Artist art1 = new Artist("opop90909", "The Beatles");
    MusicDTO mus1 = new MusicDTO("oioio0909", "Here Comes the Sun", art1);

    @Test
    public void shouldReturnTheMusic() throws Exception{
        List<MusicDTO> musics = new ArrayList<>(Arrays.asList(mus1));

        when(musicService.findByArtistOrMusic("Beatles")).thenReturn(musics);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/musicas")
                        .param("filtro", "Beatles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].name", is("Here Comes the Sun")));
    }
}