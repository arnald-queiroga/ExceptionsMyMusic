package com.ciandt.ExceptionsMyMusic.domain.dto;

import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistDTO {
    private String id;

    public PlaylistDTO(Playlist entity){
        this.id = entity.getId();
    }
}
