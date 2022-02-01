package com.istrategies.controller;

import com.istrategies.dto.RentedMovieDto;
import com.istrategies.dto.ReturnDetailDto;
import com.istrategies.entity.Movie;
import com.istrategies.entity.RentedMovie;
import com.istrategies.entity.ReturnDetail;
import com.istrategies.security.entity.User;
import com.istrategies.service.IReturnDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/return-movie")
public class ReturnDetailController {

    @Autowired
    private IReturnDetailService returnDetailService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> index(){
        return returnDetailService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRentedMovie(@PathVariable Integer id){
        return returnDetailService.getByIdMovie(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody ReturnDetailDto dto){
        User user = new User();
        Movie movie = new Movie();
        RentedMovie rentedMovie = new RentedMovie();

        user.setId(dto.getUserId());
        movie.setId(dto.getMovieId());
        movie.setTitle(dto.getMovie());
        rentedMovie.setUser(user);
        rentedMovie.setMovie(movie);
        rentedMovie.setId(dto.getRentedMovieId());
        ReturnDetail returnDetail = new ReturnDetail(rentedMovie);
        return returnDetailService.saveRentMovie(returnDetail);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return returnDetailService.deleteRentMovie(id);
    }
}
