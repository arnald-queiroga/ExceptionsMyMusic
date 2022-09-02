package com.ciandt.ExceptionsMyMusic.domain.services;

import com.ciandt.ExceptionsMyMusic.application.repositories.MusicRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Artist;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.NoContentException;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("MusicServiceTest")
public class MusicServiceTests {

    @Mock
    private MusicRepository musicRepository;
    @InjectMocks
    private MusicService musicService;

    Artist art1 = new Artist("909090xx", "The Beatles");
    Music mus1 = new Music("808080xx", "Here Comes the Sun", art1);
    Artist art2 = new Artist("707070xx", "Michael Jackson");
    Music mus2 = new Music("606060xx", "Billie Jean", art2);
    Artist art3 = new Artist("Id Artist 3", "Bruno Mars");
    Music mus3 = new Music("Id Music 3", "Talking to the moon", art3);

    @Test
    @DisplayName("shouldReturnMusicFindByNames")
    public void searchingMusicOnList() throws Exception {
        List<Music> musics = new ArrayList<>(List.of(mus1));
        String name = "Beatles";
        Mockito.when(musicRepository.findByArtistOrNameOfMusic(name)).thenReturn(musics);
        List<MusicDTO> musicsDTO = musicService.findByArtistOrMusic(name);

        assertNotNull(musicsDTO);
        assertEquals(musics.size(), musicsDTO.size());
        assertEquals("The Beatles", musicsDTO.get(0).getArtist().getName());
    }

    @Test
    @DisplayName("shouldReturnMusicSearchingInLowerCase")
    public void returnMusicInLowerCase() throws Exception {
        List<Music> musics = new ArrayList<>(List.of(mus2));
        String name = "mich";
        Mockito.when(musicRepository.findByArtistOrNameOfMusic(name)).thenReturn(musics);
        List<MusicDTO> musicDTO = musicService.findByArtistOrMusic(name);

        assertNotNull(musicDTO);
        assertEquals(musics.size(), musicDTO.size());
        assertEquals("Michael Jackson", musicDTO.get(0).getArtist().getName());
    }

    @Test
    @DisplayName("shouldReturnMusicSearchingInUpperCase")
    public void returnMusicInUpperCase() throws Exception {
        List<Music> musics = new ArrayList<>(List.of(mus3));
        String name = "BRUNO";
        Mockito.when(musicRepository.findByArtistOrNameOfMusic(name)).thenReturn(musics);
        List<MusicDTO> musicDTO = musicService.findByArtistOrMusic(name);

        assertNotNull(musicDTO);
        assertEquals(musics.size(), musicDTO.size());
        assertEquals("Bruno Mars", musicDTO.get(0).getArtist().getName());
    }

    @Test
    @DisplayName("shouldReturnErrorWhenIsLessThanTwoCharacters")
    public void shouldReturnErrorWhenIsLessThanTwoCharacters(){
        String name = "b";
        Mockito.when(musicRepository.findByArtistOrNameOfMusic(name)).thenReturn(new ArrayList<>());

        try {
            musicService.findByArtistOrMusic(name);
        } catch (Throwable e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("O filtro deve ter 3 ou mais caracteres", e.getMessage());
        }
    }

    @Test
    @DisplayName("shouldReturnWhenNotFoundNmeInDataBase")
    public void shouldReturnErrorToNotFoundInBd() throws Exception {
        String name = "Valesca";
        Mockito.when(musicRepository.findByArtistOrNameOfMusic(name)).thenReturn(new ArrayList<>());

        try {
            musicService.findByArtistOrMusic(name);
        } catch (Throwable e) {
            assertEquals(NoContentException.class, e.getClass());
        }
    }
}