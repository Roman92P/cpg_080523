package com.pivovarit.rental.domain;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;
import java.util.Optional;

class JdbcTemplateMovieRepository implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MovieId save(Movie movie) {
        jdbcTemplate.update("INSERT INTO movies(id, title, type) VALUES(?, ?, ?)", movie.getId()
          .getId(), movie.getTitle(), movie.getType().toString());
        return movie.getId();
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM movies", asMovie());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        try {
            Movie movie = jdbcTemplate.queryForObject("SELECT * FROM movies m WHERE m.title = ?", asMovie(), title);
            return Optional.ofNullable(movie);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return jdbcTemplate.query("SELECT * FROM movies m WHERE m.id = ?", asMovie(), id.getId()).stream().findAny();
    }

    @Override
    public boolean delete(MovieId id) {
        return jdbcTemplate.update("DELETE FROM movies m WHERE m.id = ?", id.getId()) == 1;
    }

    private static RowMapper<Movie> asMovie() {
        return (rs, rowNum) -> new Movie(
          new MovieId(rs.getLong("id")),
          rs.getString("title"),
          MovieType.valueOf(rs.getString("type")));
    }
}
