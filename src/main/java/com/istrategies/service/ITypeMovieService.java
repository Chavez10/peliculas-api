package com.istrategies.service;

import com.istrategies.entity.TypeMovie;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ITypeMovieService {

    ResponseEntity<?> findAll();
    Optional<TypeMovie> geTypeMovie(Integer id);
    ResponseEntity<?> getByIdTypeMovie(Integer id);
    ResponseEntity<?> saveTypeMovie(TypeMovie typeMovie, Integer id);
    ResponseEntity<?> deleteTypeMovie(Integer id);
}
