package com.istrategies.impl;

import com.istrategies.dto.RentedMovieDto;
import com.istrategies.entity.Movie;
import com.istrategies.entity.RentedMovie;
import com.istrategies.repository.IMovieRepository;
import com.istrategies.repository.IRentedMovieRepository;
import com.istrategies.service.IMovieService;
import com.istrategies.service.IRentedMovieService;
import com.istrategies.settings.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentedMovieImp implements IRentedMovieService {

    @Autowired
    private IRentedMovieRepository rentedMovieRepository;
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private IMovieService movieService;

    @Override
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<RentedMovieDto> rentedMovie =
                rentedMovieRepository.findAll()
                        .stream()
                        .map(movie -> {
                            RentedMovieDto dto = new RentedMovieDto();
                            dto.setRentedId(movie.getId());
                            dto.setUserId(movie.getUser().getId());
                            dto.setMovieId(movie.getMovie().getId());
                            dto.setMovie(movie.getMovie().getTitle());
                            dto.setUser(movie.getUser().getUsername());
                            dto.setRentDate(movie.getRentDate());
                            dto.setReturnDate(movie.getReturnDate());
                            dto.setStatus(movie.getStatus());
                            return dto;
                        }).collect(Collectors.toList());

        if (rentedMovie.size() > 0){
            response.put("rent_movie", rentedMovie);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "No records yet.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAllByUser(Integer id) {
        Map<String, Object> response = new HashMap<>();
        List<RentedMovieDto> rentedMovie =
                rentedMovieRepository.findAll(id)
                        .stream()
                        .map(movie -> {
                            RentedMovieDto dto = new RentedMovieDto();
                            dto.setRentedId(movie.getId());
                            dto.setUserId(movie.getUser().getId());
                            dto.setMovieId(movie.getMovie().getId());
                            dto.setMovie(movie.getMovie().getTitle());
                            dto.setUser(movie.getUser().getUsername());
                            dto.setRentDate(movie.getRentDate());
                            dto.setReturnDate(movie.getReturnDate());
                            dto.setStatus(movie.getStatus());
                            return dto;
                        }).collect(Collectors.toList());
        if (rentedMovie.size() > 0){
            response.put("rent_movie", rentedMovie);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "No records yet.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public Optional<RentedMovie> getMovie(Integer id) {
        Optional<RentedMovie> movie = rentedMovieRepository.findById(id);
        if (movie.isEmpty()){
            throw new NotFoundException("The requested resource does not exist.");
        }
        return movie;
    }

    @Override
    public ResponseEntity<?> getByIdMovie(Integer id) {
        Map<String, Object> response = new HashMap<>();
        RentedMovieDto dto = new RentedMovieDto();
        RentedMovie movie = this.getMovie(id).get();

        dto.setRentedId(movie.getId());
        dto.setUserId(movie.getUser().getId());
        dto.setMovieId(movie.getMovie().getId());
        dto.setMovie(movie.getMovie().getTitle());
        dto.setUser(movie.getUser().getUsername());
        dto.setRentDate(movie.getRentDate());
        dto.setReturnDate(movie.getReturnDate());
        dto.setStatus(movie.getStatus());

        response.put("rent_movie", dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveRentMovie(RentedMovie movie) {
        Map<String, Object> response = new HashMap<>();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime rtDate = today.plusDays(5);
        Date returnDate = Date.from(rtDate.toInstant(ZoneOffset.UTC));
        RentedMovie movieInst = null;
        int stock = movieRepository.findByStock(movie.getMovie().getId()) -1;

        movie.setRentDate(new Date());
        movie.setReturnDate(returnDate);
        movieInst = rentedMovieRepository.save(movie);
        movieService.updateStock(movie.getMovie().getId(), stock);
        response.put("movie", movieInst.getMovie().getTitle());
        response.put("message", "Rented movie");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteRentMovie(Integer id) {
        Map<String, Object> response = new HashMap<>();
        RentedMovie movie = this.getMovie(id).get();
        movie.setStatus(false);
        rentedMovieRepository.save(movie);
        response.put("movie", movie.getMovie().getTitle());
        response.put("message", "Record deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
