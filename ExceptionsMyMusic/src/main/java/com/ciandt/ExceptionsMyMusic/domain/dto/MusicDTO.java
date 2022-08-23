package com.ciandt.ExceptionsMyMusic.domain.dto;

import com.ciandt.ExceptionsMyMusic.domain.entities.Music;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MusicDTO {
    private String id;
    private String name;

    public MusicDTO(Music entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
