package com.ciandt.ExceptionsMyMusic.domain.dto;

import com.ciandt.ExceptionsMyMusic.domain.entities.Artist;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MusicDTO {
    private String id;
    private String name;
    private Artist artist;
}
