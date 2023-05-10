package com.pivovarit.rental.domain;

import java.util.Collection;
import java.util.Optional;

interface MovieRepository {
    MovieId save(Movie movie);

    Collection<Movie> findAll();

    Optional<Movie> findByTitle(String title);

    Optional<Movie> findById(MovieId id);

    boolean delete(MovieId id);
}
