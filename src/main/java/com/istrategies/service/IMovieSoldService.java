package com.istrategies.service;

import com.istrategies.entity.MovieSold;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IMovieSoldService {

    ResponseEntity<?> findAllMovieSold();
    Optional<MovieSold> getMovieSold(Integer id);
    ResponseEntity<?> getMovieSoldById(Integer id);
    ResponseEntity<?> saveMovieSold(MovieSold movieSold, Integer id);
    ResponseEntity<?> deleteMovieSold(Integer id);
    void prueba(Integer id);
}
