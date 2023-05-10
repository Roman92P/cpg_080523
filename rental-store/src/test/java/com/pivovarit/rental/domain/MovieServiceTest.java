package com.pivovarit.rental.domain;

import com.pivovarit.rental.domain.api.MovieAddRequest;
import com.pivovarit.rental.domain.api.MovieDto;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieServiceTest {

    @RepeatedTest(1000)
    void should_add_movie() {
        MovieFacade service = inMemoryInstance();
        MovieAddRequest newMovie = new MovieAddRequest(1, "Spiderman", "REGULAR");

        service.addMovie(newMovie);

        MovieDto movie = service.findById(1).get();

        TestUtils.equals(newMovie, movie);
        assertThat(movie.getDescription()).isEqualTo("foo");
    }

    private static MovieFacade inMemoryInstance() {
        return new MovieFacade(new InMemoryMovieRepository(), new MoviePriceCalculator(10, 8, 5), id -> "foo");
    }
}
