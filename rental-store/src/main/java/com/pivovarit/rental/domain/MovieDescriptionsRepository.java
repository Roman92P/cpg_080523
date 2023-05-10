package com.pivovarit.rental.domain;

interface MovieDescriptionsRepository {
    String getDescriptionFor(long movieId);
}
