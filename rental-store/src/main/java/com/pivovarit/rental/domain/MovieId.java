package com.pivovarit.rental.domain;

import java.util.Objects;

class MovieId {
    private final long id;

    public MovieId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieId movieId = (MovieId) o;
        return id == movieId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MovieId{" +
          "id=" + id +
          '}';
    }
}
