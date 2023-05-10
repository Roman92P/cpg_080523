package com.pivovarit.rental.domain;

import com.pivovarit.rental.domain.api.MovieAddRequest;
import com.pivovarit.rental.domain.api.MovieDto;

final class MovieConverter {

    public static Movie from(MovieAddRequest request) {
        return new Movie(new MovieId(request.getId()), request.getTitle(), MovieType.valueOf(request.getType()));
    }

    public static MovieDto from(Movie movie, String description) {
        return new MovieDto(movie.getId().getId(), movie.getTitle(), movie.getType().toString(), description);
    }
}
