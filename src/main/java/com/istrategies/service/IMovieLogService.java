package com.istrategies.service;

import com.istrategies.entity.MovieLog;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IMovieLogService {

    ResponseEntity<?> findAllLogs();
    Optional<MovieLog> getLog(Integer id);
    ResponseEntity<?> getByIdLog(Integer id);
    MovieLog saveLog(MovieLog log);
}
