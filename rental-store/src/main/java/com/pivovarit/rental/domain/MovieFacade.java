package com.pivovarit.rental.domain;

import com.pivovarit.rental.domain.api.MovieAddRequest;
import com.pivovarit.rental.domain.api.MovieDto;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.pivovarit.rental.domain.MovieConverter.from;

// https://www.archunit.org
public class MovieFacade {

    private final MovieRepository movieRepository;
    private final MoviePriceCalculator moviePriceCalculator;
    private final MovieDescriptionsRepository movieDescriptionsRepository;

    public MovieFacade(MovieRepository movieRepository, MoviePriceCalculator moviePriceCalculator, MovieDescriptionsRepository movieDescriptionsRepository) {
        this.movieRepository = movieRepository;
        this.moviePriceCalculator = moviePriceCalculator;
        this.movieDescriptionsRepository = movieDescriptionsRepository;
    }

    public void addMovie(MovieAddRequest movie) {
        movieRepository.save(from(movie));
    }

    public Collection<MovieDto> findAllByType(String movieType) {
        return movieRepository.findAll().stream()
          .filter(m -> m.getType() == MovieType.valueOf(movieType))
          .map(toEnrichedMovie())
          .collect(Collectors.toList());
    }

    private Function<Movie, MovieDto> toEnrichedMovie() {
        return movie -> MovieConverter.from(movie, movieDescriptionsRepository.getDescriptionFor(movie.getId().getId()));
    }

    public Collection<MovieDto> findAll() {
        return movieRepository.findAll().stream()
          .map(toEnrichedMovie())
          .collect(Collectors.toList());
    }

    public boolean removeById(int id) {
        return movieRepository.delete(new MovieId(id));
    }

    public Optional<MovieDto> findById(int id) {
        return movieRepository.findById(new MovieId(id))
          .map(toEnrichedMovie());

    }

    public Optional<Long> getPriceFor(int id) {
        return movieRepository.findById(new MovieId(id))
          .map(moviePriceCalculator::getPriceFor);
    }
}
