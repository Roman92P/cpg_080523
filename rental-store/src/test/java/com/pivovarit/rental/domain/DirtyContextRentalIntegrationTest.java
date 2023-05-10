package com.pivovarit.rental.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class DirtyContextRentalIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private InMemoryMovieRepository movieRepository;

    @BeforeEach
    public void before() {
        movieRepository.clear();
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
}
