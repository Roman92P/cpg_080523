package com.pivovarit.rental.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
class RentalStoreConfigurationDev {

    @Bean
    @Profile("dev")
    public MovieRepository movieRepository() {
        return new InMemoryMovieRepository();
    }

    @Bean
    @Profile("dev")
    public MovieDescriptionsRepository movieDescriptionsRepository() {
        return id -> "lorem ipsum";
    }
}
