package com.pivovarit.backoffice;

import com.pivovarit.rental.domain.MovieFacade;

public class BackOfficeFacade {

    private final MovieFacade movies;

    public BackOfficeFacade(MovieFacade movies) {
        this.movies = movies;
    }

}
