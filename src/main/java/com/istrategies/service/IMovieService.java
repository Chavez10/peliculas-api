package com.istrategies.service;

import com.istrategies.entity.Movie;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IMovieService {

    ResponseEntity<?> findAll();
    ResponseEntity<?> findByLikes();
    Optional<Movie> getMovie(Integer id);
    ResponseEntity<?> getByIdMovie(Integer id);
    ResponseEntity<?> saveMovie(Movie movie, Integer id);
    ResponseEntity<?> deleteMovie(Integer id);
    void updateStock(Integer id, int stock);
    ResponseEntity<?> giveLike(Integer id);

}
