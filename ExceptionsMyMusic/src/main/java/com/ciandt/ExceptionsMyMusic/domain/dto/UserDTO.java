package com.ciandt.ExceptionsMyMusic.domain.dto;

import com.ciandt.ExceptionsMyMusic.domain.entities.Playlist;
import com.ciandt.ExceptionsMyMusic.domain.entities.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String id;
    private String name;
    private Playlist playlist;

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
//        this.playlist = entity.getPlaylist();
    }
}
