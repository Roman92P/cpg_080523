package com.pivovarit.rental.domain;

import com.pivovarit.rental.domain.api.MovieAddRequest;
import com.pivovarit.rental.domain.api.MovieDto;

import static org.assertj.core.api.Assertions.assertThat;

public final class TestUtils {

    private TestUtils() {
    }

    public static void equals(MovieAddRequest request, Movie movie) {
        assertThat(movie.getId().getId()).isEqualTo(request.getId());
        assertThat(movie.getTitle()).isEqualTo(request.getTitle());
        assertThat(movie.getType()).asString().isEqualTo(request.getType());
    }

    public static void equals(MovieDto dto, Movie movie) {
        assertThat(movie.getId().getId()).isEqualTo(dto.getId());
        assertThat(movie.getTitle()).isEqualTo(dto.getTitle());
        assertThat(movie.getType()).asString().isEqualTo(dto.getType());
    }

    public static void equals(MovieAddRequest newMovie, MovieDto movie) {
        assertThat(movie.getId()).isEqualTo(newMovie.getId());
        assertThat(movie.getTitle()).isEqualTo(newMovie.getTitle());
        assertThat(movie.getType()).asString().isEqualTo(newMovie.getType());
    }
}
