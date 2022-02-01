package com.istrategies.controller;

import com.istrategies.dto.RentedMovieDto;
import com.istrategies.entity.Movie;
import com.istrategies.entity.RentedMovie;
import com.istrategies.security.entity.User;
import com.istrategies.service.IRentedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movie-rental")
public class RentedMovieController {

    @Autowired
    private IRentedMovieService movieService;


    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> index(){
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRentedMovie(@PathVariable Integer id){
        return movieService.getByIdMovie(id);
    }

    @GetMapping("/rent-user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findAllByUser(@PathVariable Integer id){
        return movieService.findAllByUser(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> save(@Valid @RequestBody RentedMovieDto dto){
        User user = new User();
        user.setId(dto.getUserId());
        Movie movie = new Movie();
        movie.setId(dto.getMovieId());
        movie.setTitle(dto.getMovie());
        RentedMovie rentedMovie = new RentedMovie(user, movie, dto.getRentDate(), dto.getReturnDate(), dto.getStatus());

        return movieService.saveRentMovie(rentedMovie);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return movieService.deleteRentMovie(id);
    }

}
