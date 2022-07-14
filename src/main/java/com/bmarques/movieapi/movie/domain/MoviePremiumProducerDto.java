package com.bmarques.movieapi.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MoviePremiumProducerDto {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
