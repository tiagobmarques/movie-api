package com.bmarques.movieapi.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MoviePremiumDto {

    private List<MoviePremiumProducerDto> min;
    private List<MoviePremiumProducerDto> max;
}
