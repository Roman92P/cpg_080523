package com.pivovarit.rental.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryMovieRepository implements MovieRepository {

    private final Map<MovieId, Movie> movies = new ConcurrentHashMap<>();

    @Override
    public MovieId save(Movie movie) {
        movies.put(movie.getId(), movie);
        return movie.getId();
    }

    @Override
    public Collection<Movie> findAll() {
        return movies.values();
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movies.values().stream()
          .filter(m -> m.getTitle().equals(title))
          .findAny();
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return Optional.ofNullable(movies.get(id));
    }

    @Override
    public boolean delete(MovieId id) {
        return movies.remove(id) != null;
    }

    public void clear() {
        movies.clear();
    }
}
