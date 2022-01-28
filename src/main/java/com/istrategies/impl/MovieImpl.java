package com.istrategies.impl;

import com.istrategies.dto.MovieDto;
import com.istrategies.dto.MovieLogDto;
import com.istrategies.entity.Movie;
import com.istrategies.entity.MovieLog;
import com.istrategies.repository.IMovieLogRepository;
import com.istrategies.repository.IMovieRepository;
import com.istrategies.service.IMovieService;
import com.istrategies.settings.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieImpl implements IMovieService {

    private final IMovieRepository movieRepository;
    private final IMovieLogRepository movieLogRepository;

    public MovieImpl(IMovieRepository movieRepository, IMovieLogRepository movieLogRepository) {
        this.movieRepository = movieRepository;
        this.movieLogRepository = movieLogRepository;
    }

    @Override
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<MovieDto> movies =
                movieRepository.findAll()
                        .stream()
                        .map(movie -> {
                            MovieDto dto = new MovieDto();
                            dto.setId(movie.getId());
                            dto.setTitle(movie.getTitle());
                            dto.setDescription(movie.getDescription());
                            dto.setImage(movie.getImage());
                            dto.setRentPrice(movie.getRentPrice());
                            dto.setSalesPrice(movie.getSalesPrice());
                            dto.setStock(movie.getStock());
                            dto.setAvailability(movie.isAvailability());
                            dto.setCreatedAt(movie.getCreatedAt());
                            dto.setStatus(movie.isStatus());
                            dto.setTypeMovies(movie.getTypeMovies());
                            return dto;
                        }).collect(Collectors.toList());
        if (movies.size() > 0){
            response.put("movies", movies);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "No records yet.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public Optional<Movie> getMovie(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()){
            throw new NotFoundException("The requested resource does not exist.");
        }
        return movie;
    }

    @Override
    public ResponseEntity<?> getByIdMovie(Integer id) {
        Map<String, Object> response = new HashMap<>();
        MovieDto dto = new MovieDto();
        Movie movie = this.getMovie(id).get();

        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setImage(movie.getImage());
        dto.setRentPrice(movie.getRentPrice());
        dto.setSalesPrice(movie.getSalesPrice());
        dto.setStock(movie.getStock());
        dto.setAvailability(movie.isAvailability());
        dto.setCreatedAt(movie.getCreatedAt());
        dto.setStatus(movie.isStatus());
        dto.setTypeMovies(movie.getTypeMovies());

        response.put("movie", dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveMovie(Movie movie, Integer id) {
        Map<String, Object> response = new HashMap<>();
        Movie movieInst = null;
        Movie movieUpd = null;

        if (id == null){
            movieInst = movieRepository.save(movie);
            response.put("movie", movieInst.getTitle());
            response.put("message", "Record saved.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        movieUpd = this.getMovie(id).get();

        movieUpd.setTitle(movie.getTitle());
        movieUpd.setDescription(movie.getDescription());
        movieUpd.setImage(movie.getImage());
        movieUpd.setRentPrice(movie.getRentPrice());
        movieUpd.setSalesPrice(movie.getSalesPrice());
        movieUpd.setStock(movie.getStock());
        movieUpd.setAvailability(movie.isAvailability());
        movieUpd.setStatus(movie.isStatus());
        movieUpd.setTypeMovies(movie.getTypeMovies());

        movieInst = movieRepository.save(movieUpd);
        MovieLog log = new MovieLog(
                movieInst, movie.getTitle(),
                movie.getRentPrice(), movie.getSalesPrice());
        movieLogRepository.save(log);
        response.put("movie", movieInst.getTitle());
        response.put("message", "Updated registry.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteMovie(Integer id) {
        Map<String, Object> response = new HashMap<>();
        Movie movie = this.getMovie(id).get();
        movie.setStatus(false);
        movieRepository.save(movie);
        response.put("movie", movie.getTitle());
        response.put("message", "Record deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
