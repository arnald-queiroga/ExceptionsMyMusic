package com.ciandt.ExceptionsMyMusic.domain.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Musicas")
public class Music {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Id")
    private String id;

    @Column(name = "Nome")
    private String name;

    @ManyToOne
    @JoinColumn(name = "ArtistaId", nullable = false)
    private Artist artist;

    @ManyToMany(mappedBy = "musics")
    private Set<Playlist> playlists = new HashSet<>();
}
