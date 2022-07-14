package com.bmarques.movieapi.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "movie")
@NoArgsConstructor
@Getter
public class MovieEntity {

    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "premium_year")
    private String year;

    @Column
    private String title;

    @Column
    private String studio;

    @Column
    private String producer;

    @Column
    private Boolean winner;
}
