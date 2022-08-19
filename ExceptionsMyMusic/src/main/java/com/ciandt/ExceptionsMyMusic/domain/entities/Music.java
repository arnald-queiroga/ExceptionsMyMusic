package com.ciandt.ExceptionsMyMusic.domain.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
}
