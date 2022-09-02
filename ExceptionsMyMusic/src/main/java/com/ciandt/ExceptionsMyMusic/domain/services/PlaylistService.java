package com.ciandt.ExceptionsMyMusic.domain.services;

import com.ciandt.ExceptionsMyMusic.application.repositories.MusicRepository;
import com.ciandt.ExceptionsMyMusic.application.repositories.PlaylistRepository;
import com.ciandt.ExceptionsMyMusic.domain.dto.MusicDTO;
import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.DatabaseException;
import com.ciandt.ExceptionsMyMusic.domain.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class PlaylistService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PlaylistService.class);
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private MusicRepository musicRepository;

    @Transactional
    public void addMusicToPlaylist(String playlistID, MusicDTO musicDTO) {

        if (playlistRepository.findById(playlistID).isEmpty()) {
            LOGGER.error("Playlist not found for search performed.");
            throw new ResourceNotFoundException("Playlist not found!");
        } else {
            if (musicRepository.findById(musicDTO.getId()).isEmpty()) {
                LOGGER.error("Music not found in the playlist for the search performed");
                throw new ResourceNotFoundException("Music not found!");
            } else {
                Playlist playlist = playlistRepository.findById(playlistID).get();
                Set<Music> musics = playlist.getMusics();

                for (Music music : musics) {
                    if (music.getId().equals(musicDTO.getId())) {
                        LOGGER.error("Music already exists in the playlist");
                        throw new ResourceNotFoundException("Existing song in the playlist!");
                    }
                }
                Music musicToAdd = musicRepository.findById(musicDTO.getId()).get();
                playlist.getMusics().add(musicToAdd);
                musicToAdd.getPlaylists().add(playlist);
                LOGGER.info("Music add to playlist!");
                playlistRepository.save(playlist);
            }
        }
    }

    @Transactional
    public void removeMusicToPlaylist(String playlistID, String musicID) {
        try {
            Optional<Playlist> playlist = playlistRepository.findById(playlistID);
            String music = playlistRepository.findMusicByPlaylists(playlistID, musicID);
            if (playlist.isEmpty()) {
                throw new ResourceNotFoundException("Playlist not found!");
            } else if (music == null) {
                throw new ResourceNotFoundException("Music not found in playlist!");
            }
            playlistRepository.removeMusicFromPlaylist(playlistID, musicID);
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}