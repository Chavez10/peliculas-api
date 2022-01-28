package com.istrategies.controller;

import com.istrategies.dto.TypeMovieDto;
import com.istrategies.entity.TypeMovie;
import com.istrategies.service.ITypeMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/type-movie")
public class TypeMovieController {

    @Autowired
    private ITypeMovieService typeMovieService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> index(){
        return typeMovieService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return typeMovieService.getByIdTypeMovie(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody TypeMovieDto dto){
        TypeMovie typeMovie = new TypeMovie(dto.getTypeMovie(), dto.isStatus());
        return typeMovieService.saveTypeMovie(typeMovie, null);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody TypeMovieDto dto, @PathVariable Integer id){
        TypeMovie typeMovie = new TypeMovie(dto.getTypeMovie(), dto.isStatus());
        return typeMovieService.saveTypeMovie(typeMovie, id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return typeMovieService.deleteTypeMovie(id);
    }

}
