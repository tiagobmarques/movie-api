package com.bmarques.movieapi.movie.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public MoviePremiumDto getAwardWinningProducers() {
        List<MovieEntity> movieList = movieRepository.findByWinnerTrue();
        List<String> allWinnerProducer = getAllWinnerProducer(movieList);

        return getMinAndMaxWinnerByInterval(movieList, allWinnerProducer);
    }

    private List<String> getAllWinnerProducer(List<MovieEntity> movieList) {

        return movieList.stream()
                .map(MovieEntity::getProducer)
                .map(producer -> producer.replace(" and", ",")
                        .replace(",,", ","))
                .map(s -> Arrays.asList(s.split("\\s*,\\s*")))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

    }

    private MoviePremiumDto getMinAndMaxWinnerByInterval(List<MovieEntity> movieList, List<String> allWinnerProducer) {
        int longerInterval = 0;
        int smallerInterval = 999;
        var moviePremiumDtoMin = new ArrayList<MoviePremiumProducerDto>();
        var moviePremiumDtoMax = new ArrayList<MoviePremiumProducerDto>();

        for (String producer : allWinnerProducer) {
            List<MovieEntity> winnerMovies = movieList.stream()
                    .filter(movies -> movies.getProducer().contains(producer))
                    .sorted(Comparator.comparing(MovieEntity::getYear))
                    .collect(Collectors.toList());

            boolean producerHaveTwoPremium = winnerMovies.size() > 1;
            if (producerHaveTwoPremium){
                int oldestPremium = winnerMovies.stream()
                        .mapToInt(movie -> Integer.parseInt(movie.getYear()))
                        .min().orElseThrow(NoSuchElementException::new);

                int newestPremium = winnerMovies.stream()
                        .mapToInt(movie -> Integer.parseInt(movie.getYear()))
                        .max().orElseThrow(NoSuchElementException::new);

                int interval = newestPremium - oldestPremium;
                if (interval >= longerInterval) {
                    if (interval > longerInterval) moviePremiumDtoMax.clear();
                    moviePremiumDtoMax.add(new MoviePremiumProducerDto(producer, interval, oldestPremium, newestPremium));
                    longerInterval = interval;
                }
                if (interval <= smallerInterval) {
                    if (interval < smallerInterval) moviePremiumDtoMin.clear();
                    moviePremiumDtoMin.add(new MoviePremiumProducerDto(producer, interval, oldestPremium, newestPremium));
                    smallerInterval = interval;
                }
            }
        }

        return new MoviePremiumDto(moviePremiumDtoMin, moviePremiumDtoMax);
    }
}
