package com.ciandt.ExceptionsMyMusic.tests;

import com.ciandt.ExceptionsMyMusic.domain.dto.PlaylistDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Artist;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import com.ciandt.ExceptionsMyMusic.domain.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class Factory {
    public static Playlist createPlayList(){

        Artist artist = new Artist("32844fdd-bb76-4c0a-9627-e34ddc9fd892", "The Beatles");
        Music music = new Music("03c86d1e-d3a0-462e-84a9-755cfc49aab8", "Reminiscing");
        Playlist playlist = new Playlist("a39926f4-6acb-4497-884f-d4e5296ef652");

        playlist.getMusics().add(music);
        return playlist;
    }

    public static PlaylistDTO createdPlaylistDTO(){
        Playlist playlist = createPlayList();
        return new PlaylistDTO();
    }
}
