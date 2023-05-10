package com.pivovarit.rental.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class DirtyContextRentalIntegrationWithConfigOverrideTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    public void before() {
        TestRepositoryConfig.movieRepository.clear();
    }

    @Test
    void test1() throws Exception {
        movieRepository.save(new Movie(new MovieId(1), "absdas", MovieType.OLD));
        System.out.println(movieRepository.findAll());
    }

    @Test
    void test2() throws Exception {
        movieRepository.save(new Movie(new MovieId(2), "absdas2", MovieType.NEW));
        System.out.println(movieRepository.findAll());
    }

    @TestConfiguration
    public static class TestRepositoryConfig {
        public static InMemoryMovieRepository movieRepository = new InMemoryMovieRepository();

        @Bean
        @Primary
        public MovieRepository inMemoryTestMovieRepository() {
            return movieRepository;
        }
    }

}
