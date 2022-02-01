package com.istrategies.service;

import com.istrategies.entity.RentedMovie;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IRentedMovieService {

    ResponseEntity<?> findAll();
    ResponseEntity<?> findAllByUser(Integer id);
    Optional<RentedMovie> getMovie(Integer id);
    ResponseEntity<?> getByIdMovie(Integer id);
    ResponseEntity<?> saveRentMovie(RentedMovie movie);
    ResponseEntity<?> deleteRentMovie(Integer id);
}
