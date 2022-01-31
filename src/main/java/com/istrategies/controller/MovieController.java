package com.istrategies.controller;

import com.istrategies.dto.MovieDto;
import com.istrategies.entity.Movie;
import com.istrategies.impl.CloudinaryService;
import com.istrategies.service.IMovieService;
import com.istrategies.settings.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private String urlImage;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private CloudinaryService cloudinaryService;



    @GetMapping("/home/{filter}")
    public ResponseEntity<?> index(@PathVariable String filter){
        if (filter.equals("likes")){
            return movieService.findByLikes();
        }
        return movieService.findAll();
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        Map<String, Object> response = new HashMap<>();
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null){
            throw new BadRequestException("Formato no valido en la imagen");
        }
        Map result = cloudinaryService.upload(multipartFile);
        //urlImage = (String) result.get("url");
        response.put("imagen", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
