package com.pivovarit.rental.domain.api;

public class MovieDto {
    private final long id;
    private final String title;
    private final String type;
    private final String description;

    public MovieDto(long id, String title, String type, String description) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
          "id=" + id +
          ", title='" + title + '\'' +
          ", type='" + type + '\'' +
          ", description='" + description + '\'' +
          '}';
    }
}
