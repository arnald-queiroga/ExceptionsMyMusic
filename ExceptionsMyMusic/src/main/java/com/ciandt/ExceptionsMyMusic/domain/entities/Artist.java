package com.ciandt.ExceptionsMyMusic.domain.entities;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "Artistas")
public class Artist {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Id")
    private String id;

    @Column(name = "Nome")
    private String name;

    @OneToMany(mappedBy = "artist")
    private Set<Music> musics = new HashSet<>();
}
