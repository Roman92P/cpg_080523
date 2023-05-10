package com.pivovarit.rental.domain;

import com.pivovarit.rental.domain.api.MovieAddRequest;
import com.pivovarit.rental.domain.api.MovieDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class RentalIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private InMemoryMovieRepository movieRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void before() {
        movieRepository.clear();
    }

    @Test
    void shouldAddMovie() throws Exception {
        MovieAddRequest newMovie = new MovieAddRequest(1, "Spiderman", MovieType.REGULAR.toString());

        testRestTemplate.postForObject("http://localhost:{port}/movies", newMovie, String.class, port);
        ResponseEntity<MovieDto> response = testRestTemplate.getForEntity(
          "http://localhost:{port}/movies/{id}",
          MovieDto.class, port, newMovie.getId());

        // then
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        assertThat(response.getBody().getTitle()).isEqualTo(newMovie.getTitle());
    }

    @Test
    void shouldGetMovie() {
        // given
        Movie movie = new Movie(new MovieId(1), "Spiderman", MovieType.REGULAR);
        movieRepository.save(movie);

        // when
        ResponseEntity<MovieDto> response = testRestTemplate.getForEntity(
          "http://localhost:{port}/movies/{id}",
          MovieDto.class, port, movie.getId().getId());

        // then
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        TestUtils.equals(response.getBody(), movie);
    }

    @Test
    void shouldGetMoviePrice() {
        // given
        Movie movie = new Movie(new MovieId(1), "Spiderman", MovieType.REGULAR);
        movieRepository.save(movie);

        // when
        MoviePrice price = testRestTemplate.getForObject(
          "http://localhost:{port}/movies/{id}/price",
          MoviePrice.class, port, movie.getId().getId());

        // then
        assertThat(price.price).isEqualTo(8);
    }

    @Test
    void shouldDeleteMovie() {
        // given
        Movie movie = new Movie(new MovieId(1), "Spiderman", MovieType.REGULAR);
        movieRepository.save(movie);

        // when
        /*
            łatwiejsza metoda, ale nie pozwala sprawdzić kodu odpowiedzi:
            testRestTemplate.delete("http://localhost:{port}/movies/{id}", port, movie.getId().getId());
        */
        ResponseEntity<Void> deleteResponse = testRestTemplate.exchange(
          "http://localhost:{port}/movies/{id}",
          HttpMethod.DELETE, HttpEntity.EMPTY, Void.class, port, movie.getId().getId());

        ResponseEntity<MovieDto> response = testRestTemplate.getForEntity(
          "http://localhost:{port}/movies/{id}",
          MovieDto.class, port, movie.getId().getId());

        System.out.println(deleteResponse.getStatusCode());

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void shouldGetMovies() {
        // given
        Movie movie1 = new Movie(new MovieId(1), "Spiderman", MovieType.REGULAR);
        Movie movie2 = new Movie(new MovieId(2), "Spiderma2", MovieType.NEW);
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        // when
        ResponseEntity<List<MovieDto>> response = testRestTemplate.exchange(
          "http://localhost:{port}/movies", HttpMethod.GET,
          HttpEntity.EMPTY, new ParameterizedTypeReference<List<MovieDto>>() {
          },
          port);

        List<MovieDto> body = response.getBody();

        // then
        assertThat(body)
          .extracting(MovieDto::getId)
          .containsExactlyInAnyOrder(movie1.getId().getId(), movie2.getId().getId());
    }

    public static class MoviePrice {
        public Long price;
    }
}
