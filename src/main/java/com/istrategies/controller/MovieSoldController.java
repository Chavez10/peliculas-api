package com.istrategies.controller;

import com.istrategies.dto.MovieSoldDto;
import com.istrategies.entity.Movie;
import com.istrategies.entity.MovieSold;
import com.istrategies.security.entity.User;
import com.istrategies.service.IMovieSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movie-sold")
public class MovieSoldController {

    @Autowired
    private IMovieSoldService movieSoldService;


    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> index(){
        return movieSoldService.findAllMovieSold();
    }

    @GetMapping("/sold-movie/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> soldMovie(@PathVariable Integer id){
        return movieSoldService.findAllByUser(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getMovieSold(@PathVariable Integer id){
        return movieSoldService.getMovieSoldById(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> save(@Valid @RequestBody MovieSoldDto dto){
        User user = new User();
        user.setId(dto.getIdUser());
        Movie movie = new Movie();
        movie.setId(dto.getIdMovie());
        movie.setTitle(dto.getMovie());
        MovieSold movieSold = new MovieSold(user, movie, dto.getStatus());
        return movieSoldService.saveMovieSold(movieSold, null);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody MovieSoldDto dto, @PathVariable Integer id){
        User user = new User();
        user.setId(dto.getIdUser());
        Movie movie = new Movie();
        movie.setId(dto.getIdMovie());
        movie.setTitle(dto.getMovie());
        MovieSold movieSold = new MovieSold(user, movie, dto.getStatus());
        return movieSoldService.saveMovieSold(movieSold, id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return movieSoldService.deleteMovieSold(id);
    }
}
