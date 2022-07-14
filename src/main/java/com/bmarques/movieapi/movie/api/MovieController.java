package com.bmarques.movieapi.movie.api;

import com.bmarques.movieapi.movie.domain.MoviePremiumDto;
import com.bmarques.movieapi.movie.domain.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService service;

    @ApiOperation(value = "Get Award Winning Producers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Producers found"),
    })
    @GetMapping("/premium")
    public MoviePremiumDto getAllMovies() {
        return service.getAwardWinningProducers();
    }
}
