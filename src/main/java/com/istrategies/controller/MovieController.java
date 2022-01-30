package com.istrategies.controller;

import com.istrategies.dto.MovieDto;
import com.istrategies.entity.Movie;
import com.istrategies.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;


    @GetMapping("/home/{filter}")
    public ResponseEntity<?> index(@PathVariable String filter){
        if (filter.equals("likes")){
            return movieService.findByLikes();
        }
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getByIdMovie(@PathVariable Integer id){
        return movieService.getByIdMovie(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody MovieDto dto){
        Movie movie = new Movie(
                dto.getTitle(), dto.getDescription(),
                dto.getImage(), dto.getRentPrice(),
                dto.getSalesPrice(), dto.getStock(),
                dto.isAvailability(), dto.isStatus());
        movie.setTypeMovies(dto.getTypeMovies());

        return movieService.saveMovie(movie, null);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody MovieDto dto, @PathVariable Integer id){
        Movie movie = new Movie(
                dto.getTitle(), dto.getDescription(),
                dto.getImage(), dto.getRentPrice(),
                dto.getSalesPrice(), dto.getStock(),
                dto.isAvailability(), dto.isStatus());
        movie.setTypeMovies(dto.getTypeMovies());

        return movieService.saveMovie(movie, id);
    }

    @PutMapping("/likes/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> giveLikes(@PathVariable Integer id){
        return movieService.giveLike(id);
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return movieService.deleteMovie(id);
    }
}
