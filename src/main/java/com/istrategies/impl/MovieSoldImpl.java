package com.istrategies.impl;

import com.fasterxml.jackson.core.JsonToken;
import com.istrategies.dto.MovieDto;
import com.istrategies.dto.MovieSoldDto;
import com.istrategies.entity.Movie;
import com.istrategies.entity.MovieSold;
import com.istrategies.repository.IMovieRepository;
import com.istrategies.repository.IMovieSoldRepository;
import com.istrategies.service.IMovieService;
import com.istrategies.service.IMovieSoldService;
import com.istrategies.settings.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieSoldImpl implements IMovieSoldService {

    @Autowired
    private IMovieService movieService;
    private final IMovieSoldRepository movieSoldRepository;
    private final IMovieRepository movieRepository;



    public MovieSoldImpl(IMovieSoldRepository movieSoldRepository, IMovieRepository movieRepository) {
        this.movieSoldRepository = movieSoldRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public ResponseEntity<?> findAllMovieSold() {
        Map<String, Object> response = new HashMap<>();
        List<MovieSoldDto> movieSold =
                movieSoldRepository
                        .findAll()
                        .stream()
                        .map(movie -> {
                            MovieSoldDto dto = new MovieSoldDto();
                            dto.setIdUser(movie.getUser().getId());
                            dto.setIdMovie(movie.getMovie().getId());
                            dto.setUser(movie.getUser().getUsername());
                            dto.setMovie(movie.getMovie().getTitle());
                            dto.setDatePurchase(movie.getDatePurchase());
                            dto.setStatus(movie.getStatus());
                            return dto;
                        }).collect(Collectors.toList());
        if (movieSold.size() > 0){
            response.put("movie_sold", movieSold);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "No records yet.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAllByUser(Integer id) {
        Map<String, Object> response = new HashMap<>();
        List<MovieSoldDto> movieSold =
                movieSoldRepository.findAll(id)
                        .stream()
                        .map(movie -> {
                            MovieSoldDto dto = new MovieSoldDto();
                            dto.setIdUser(movie.getUser().getId());
                            dto.setIdMovie(movie.getMovie().getId());
                            dto.setUser(movie.getUser().getUsername());
                            dto.setMovie(movie.getMovie().getTitle());
                            dto.setDatePurchase(movie.getDatePurchase());
                            dto.setStatus(movie.getStatus());
                            return dto;
                        }).collect(Collectors.toList());
        if (movieSold.size() > 0){
            response.put("movie_sold", movieSold);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "No records yet.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public Optional<MovieSold> getMovieSold(Integer id) {
        Optional<MovieSold> movieSold = movieSoldRepository.findById(id);
        if (movieSold.isEmpty()){
            throw new NotFoundException("The requested resource does not exist.");
        }
        return movieSold;
    }

    @Override
    public ResponseEntity<?> getMovieSoldById(Integer id) {
        Map<String, Object> response = new HashMap<>();
        MovieSoldDto dto = new MovieSoldDto();
        MovieSold movie = this.getMovieSold(id).get();

        dto.setIdUser(movie.getUser().getId());
        dto.setIdMovie(movie.getMovie().getId());
        dto.setUser(movie.getUser().getUsername());
        dto.setMovie(movie.getMovie().getTitle());
        dto.setDatePurchase(movie.getDatePurchase());
        dto.setStatus(movie.getStatus());

        response.put("movie_sold", dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveMovieSold(MovieSold movieSold, Integer id) {
        Map<String, Object> response = new HashMap<>();
        MovieSold movieInst = null;
        MovieSold movieUpt = null;
        int stock, newStock;

        if (id == null){
            movieInst = movieSoldRepository.save(movieSold);
            stock = movieRepository.findByStock(movieSold.getMovie().getId());
            newStock = stock - 1;
            movieService.updateStock(movieSold.getMovie().getId(), newStock);
            response.put("movie_sold", movieInst.getMovie().getTitle());
            response.put("message", "Record saved.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        movieUpt = this.getMovieSold(id).get();
        movieUpt.setMovie(movieSold.getMovie());
        movieUpt.setUser(movieSold.getUser());

        movieInst = movieSoldRepository.save(movieUpt);

        response.put("movie_sold", movieSold.getMovie().getTitle());
        response.put("message", "Updated registry.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteMovieSold(Integer id) {
        Map<String, Object> response = new HashMap<>();
        MovieSold movieSold = this.getMovieSold(id).get();
        movieSold.setStatus(false);
        movieSoldRepository.save(movieSold);
        response.put("movie", movieSold.getMovie().getTitle());
        response.put("message", "Record deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public void prueba(Integer id) {
        List<MovieSold> dto =
                movieSoldRepository.findAll(id);
        for (MovieSold movieSold : dto){
            System.out.println(movieSold.getMovie().getTitle());
            System.out.println(movieSold.getUser().getUsername());
            System.out.println(movieSold.getDatePurchase());
        }
    }
}
