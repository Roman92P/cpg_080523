package com.pivovarit.rental.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
class RentalStoreConfiguration {

    @Bean
    public RestTemplate restTemplate(List<HttpMessageConverter<?>> messageConverters) {
        return new RestTemplate(messageConverters);
    }

    @Bean
    public MoviePriceCalculator moviePriceCalculator(
      @Value("${rental.price.new}") long priceNew,
      @Value("${rental.price.regular}") long priceRegular,
      @Value("${rental.price.old}") long priceOld) {
        return new MoviePriceCalculator(priceNew, priceRegular, priceOld);
    }

    @Bean
    public MovieFacade movieService(
      MovieRepository movieRepository,
      MoviePriceCalculator moviePriceCalculator,
      MovieDescriptionsRepository movieDescriptionsRepository) {
        return new MovieFacade(movieRepository, moviePriceCalculator, movieDescriptionsRepository);
    }
}
