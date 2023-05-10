package com.pivovarit.rental;

import com.pivovarit.rental.domain.api.MovieAddRequest;
import com.pivovarit.rental.domain.api.MovieDto;
import com.pivovarit.rental.domain.MovieFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RentalController {

    private final MovieFacade movieService;

    RentalController(MovieFacade movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{id}")
    public MovieDto movie(@PathVariable int id) {
        return movieService.findById(id)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "movie not found"));
    }

    @DeleteMapping("/movies/{id}")
    public boolean removeMovie(@PathVariable int id) {
        return movieService.removeById(id);
    }

    @GetMapping("/movies/{id}/price")
    public Map<String, Long> moviePrice(@PathVariable int id) {
        return movieService.getPriceFor(id)
          .map(price -> of("price", price))
          .orElseGet(Collections::emptyMap);
    }

    @GetMapping("/movies")
    public Collection<MovieDto> movies(@RequestParam(required = false) String type) {
        if (type != null) {
            return movieService.findAllByType(type);
        } else return movieService.findAll();
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody MovieAddRequest movie) {
        movieService.addMovie(movie);
    }

    private static Map<String, Long> of(String key, Long value) {
        Map<String, Long> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
