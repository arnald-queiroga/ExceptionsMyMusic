package com.ciandt.ExceptionsMyMusic.domain.services;

import com.ciandt.ExceptionsMyMusic.application.repositories.MusicRepository;
import com.ciandt.ExceptionsMyMusic.application.repositories.PlaylistRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Artist;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Testeesclusão de musica da PlaylistService")
public class PlaylistServiceTest {
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private MusicRepository musicRepository;

    @Mock
    private MusicService musicService;

    @InjectMocks
    private PlaylistService playlistService;

    Artist art1 = new Artist("909090xx", "The Beatles");
    Music mus1 = new Music("808080xx", "Here Comes the Sun", art1);
    Artist art2 = new Artist("707070xx", "Michael Jackson");
    Music mus2 = new Music("606060xx", "Billie Jean", art2);

    @Test
    public void shouldThrowAnExceptionWhenRemovingTheMusicIfItCanNotFindThePlaylist() throws Exception {
        String playlistId = "Id da playlist não encontrado!";

        when(playlistRepository.findById(playlistId)).thenReturn(null);

        try {
            playlistRepository.removeMusicFromPlaylist(playlistId, mus1.getId());
        } catch (Throwable e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Playlist não encontrada!", e.getMessage());
        }
    }

    @Test
    public void sholdRemoveMusicToPLaylist(){

    }


    @Test
    public void shouldThrowAnExceptionWhenRemovingTheSongIfItCanNotFindTheSongInThePlaylist() throws Exception {
        String playlistID = "a39926f4-6acb-4497-884f-d4e5296ef652";
        String musicID = null;

        try {
            playlistRepository.removeMusicFromPlaylist(playlistID, musicID);
        } catch (Throwable e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Música não encontrada na playlist!", e.getMessage());
        }
    }

    @Test
    public void removeMusicFromPlaylist() {
//        Artist art1 = new Artist("909090xx", "The Beatles");
//        Music mus1 = new Music("808080xx", "Here Comes the Sun", art1);
        String musicId = "03c86d1e-d3a0-462e-84a9-755cfc49aab8";

        String playlist = "a39926f4-6acb-4497-884f-d4e5296ef652";

        List<MusicDTO> musicDTO = musicService.findByArtistOrMusic(musicId);

        String musica = "c96b8f6f-4049-4e6b-8687-82e29c05b735";
        Mockito.when(playlistRepository.findMusicByPlaylists(playlist, musica)).thenReturn(String.valueOf(Optional.of(new Playlist())));
        Mockito.when(playlistRepository.findById(playlist)).thenReturn(Optional.of(new Playlist(playlist)));
        Mockito.when(musicService.findByArtistOrMusic("Here Comes the Sun")).thenReturn(musicDTO);
        Playlist playlistMusica = new Playlist(playlist);
        playlistService.removeMusicToPlaylist(playlist, musica);
        Assertions.assertNotEquals(playlistMusica, playlistRepository.findMusicByPlaylists(playlist, musicId));
    }
}