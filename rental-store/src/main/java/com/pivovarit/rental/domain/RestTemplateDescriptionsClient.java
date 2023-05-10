package com.pivovarit.rental.domain;

import org.springframework.web.client.RestTemplate;

class RestTemplateDescriptionsClient implements MovieDescriptionsRepository{

    private final String url;
    private final RestTemplate restTemplate;

    public RestTemplateDescriptionsClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getDescriptionFor(long movieId) {
        return restTemplate.getForObject(url + "/descriptions/{id}", Description.class, movieId).description;
    }

    private static class Description {
        public String description;
    }

}
