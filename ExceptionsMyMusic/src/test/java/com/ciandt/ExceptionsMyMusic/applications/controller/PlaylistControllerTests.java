package com.ciandt.ExceptionsMyMusic.applications.controller;

import com.ciandt.ExceptionsMyMusic.application.controllers.PlaylistController;
import com.ciandt.ExceptionsMyMusic.domain.dto.DataDTO;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.dto.PlaylistDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Artist;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import com.ciandt.ExceptionsMyMusic.domain.services.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaylistController.class)
public class PlaylistControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlaylistService service;

    private PlaylistDTO playlistDTO;

    private Playlist playlist;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlaylistController playlistController;

    @Test
    public void insertShouldReturnPlaylistCreated() throws Exception {
        Artist artist = new Artist("32844fdd-bb76-4c0a-9627-e34ddc9fd892", "The Beatles");
        Music music = new Music("03c86d1e-d3a0-462e-84a9-755cfc49aab8", "Reminiscing");
        MusicDTO musicDTO = new MusicDTO("hjhsksoisois", "hoiuaouaoau", artist);
        List<MusicDTO> listMusic = new ArrayList<>();
        listMusic.add(musicDTO);
        DataDTO dataDTO = new DataDTO(listMusic);

        Mockito.doNothing().when(service).addMusicToPlaylist("a39926f4-6acb-4497-884f-d4e5296ef652", musicDTO);

        String jsonBody = objectMapper.writeValueAsString(dataDTO);
        ResultActions result =
                mockMvc.perform(post("/playlists/a39926f4-6acb-4497-884f-d4e5296ef652/musicas")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
    }

    @Test
    public void findByPlaylistIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        String nonExistingId =  "jhsoisois";

        ResultActions result =
                mockMvc.perform(get("/{playlistId}/musicas", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void findByMusicIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        String nonExistingId = "jhsoisois";

        ResultActions result =
                mockMvc.perform(get("/{playlistId}/musicas", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }
}