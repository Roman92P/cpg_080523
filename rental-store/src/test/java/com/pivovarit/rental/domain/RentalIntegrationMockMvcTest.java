package com.pivovarit.rental.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pivovarit.rental.domain.api.MovieAddRequest;
import com.pivovarit.rental.domain.api.MovieDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class RentalIntegrationMockMvcTest {

    @Autowired
    private InMemoryMovieRepository movieRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void before() {
        movieRepository.clear();
    }

    @Test
    void shouldAddMovie() throws Exception {
        MovieAddRequest newMovie = new MovieAddRequest(1, "Spiderman", MovieType.REGULAR.toString());

        mvc.perform(post("/movies")
            .content(objectMapper.writeValueAsString(newMovie))
            .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/movies/{id}", newMovie.getId()))
          .andExpect(status().is2xxSuccessful())
          .andExpect(jsonPath("$.title", equalTo("Spiderman")));
    }

    @Test
    void shouldGetMovie() throws Exception {
        // given
        Movie movie = new Movie(new MovieId(1), "Spiderman", MovieType.REGULAR);
        movieRepository.save(movie);

        // when
        String response = mvc.perform(get("/movies/{id}", movie.getId().getId()))
          .andExpect(status().is2xxSuccessful())
          .andReturn().getResponse().getContentAsString();

        MovieDto returnedMovie = objectMapper.readValue(response, MovieDto.class);

        // then
        TestUtils.equals(returnedMovie, movie);
    }
}
