package com.ciandt.ExceptionsMyMusic.domain.services;

import com.ciandt.ExceptionsMyMusic.application.repositories.MusicRepository;
import com.ciandt.ExceptionsMyMusic.application.repositories.PlaylistRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicRepository musicRepository;

    public void addMusicToPlaylist(String playlistID, MusicDTO musicDTO) {

        // TODO tratar exception quando não encontrar playlistID
        if (playlistRepository.findById(playlistID).isEmpty()) {
            System.out.println("PlaylistID não encontrado!");
        } else {

            // TODO tratar exception quando não encontrar musicID
            if (musicRepository.findById(musicDTO.getId()).isEmpty()) {
                System.out.println("MusicID não encontrado");
            } else {
                Playlist playlist = playlistRepository.findById(playlistID).get();
                Set<Music> musics = playlist.getMusics();

                boolean flag = false;

                for (Music music : musics) {
                    // TODO tratar exception quando existir uma música na playlist, apagar tudo que faz referência à flag (ao invés de flag e break trocar peca exception)
                    if (music.getId().equals(musicDTO.getId())) {
                        flag = true;
                        break;
                    }
                }

                if (flag)
                    System.out.println("MusicID existente na playlist!");
                else {
                    Music musicToAdd = musicRepository.findById(musicDTO.getId()).get();
                    playlist.getMusics().add(musicToAdd);
                    musicToAdd.getPlaylists().add(playlist);
                    playlistRepository.save(playlist);
                }
            }
        }
    }
}
