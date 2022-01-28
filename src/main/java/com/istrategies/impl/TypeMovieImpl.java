package com.istrategies.impl;

import com.istrategies.dto.TypeMovieDto;
import com.istrategies.entity.TypeMovie;
import com.istrategies.repository.ITypeMovieRepository;
import com.istrategies.service.ITypeMovieService;
import com.istrategies.settings.exception.NotFoundException;
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
public class TypeMovieImpl implements ITypeMovieService {

    private final ITypeMovieRepository typeMovieRepository;

    public TypeMovieImpl(ITypeMovieRepository typeMovieRepository) {
        this.typeMovieRepository = typeMovieRepository;
    }

    @Override
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<TypeMovieDto> typeMovies =
                typeMovieRepository
                        .findAll()
                        .stream()
                        .map(typeMovie -> {
                            TypeMovieDto dto = new TypeMovieDto();
                            dto.setId(typeMovie.getId());
                            dto.setTypeMovie(typeMovie.getTypeMovie());
                            dto.setCreatedAt(typeMovie.getCreatedAt());
                            dto.setStatus(typeMovie.isStatus());
                            return dto;
                        }).collect(Collectors.toList());
        if (typeMovies.size() > 0){
            response.put("type_movie", typeMovies);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "No records yet.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public Optional<TypeMovie> geTypeMovie(Integer id) {
        Optional<TypeMovie> typeMovie = typeMovieRepository.findById(id);
        if (typeMovie.isEmpty()){
            throw new NotFoundException("The requested resource does not exist.");
        }
        return typeMovie;
    }

    @Override
    public ResponseEntity<?> getByIdTypeMovie(Integer id) {
        Map<String, Object> response = new HashMap<>();
        TypeMovieDto dto = new TypeMovieDto();
        TypeMovie typeMovie = this.geTypeMovie(id).get();

        dto.setId(typeMovie.getId());
        dto.setTypeMovie(typeMovie.getTypeMovie());
        dto.setCreatedAt(typeMovie.getCreatedAt());
        dto.setStatus(typeMovie.isStatus());

        response.put("type_movie", dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveTypeMovie(TypeMovie typeMovie, Integer id) {
        Map<String, Object> response = new HashMap<>();
        TypeMovie typeMovieInst = null;
        TypeMovie typeMovieUpd = null;

        if (id == null){
            typeMovieInst = typeMovieRepository.save(typeMovie);
            response.put("type_movie", typeMovieInst.getTypeMovie());
            response.put("message", "Record saved.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        typeMovieUpd = this.geTypeMovie(id).get();

        typeMovieUpd.setTypeMovie(typeMovie.getTypeMovie());
        typeMovieUpd.setStatus(typeMovie.isStatus());

        typeMovieInst = typeMovieRepository.save(typeMovieUpd);
        response.put("type_movie", typeMovieInst.getTypeMovie());
        response.put("message", "Updated registry.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteTypeMovie(Integer id) {
        Map<String, Object> response = new HashMap<>();
        TypeMovie typeMovie = this.geTypeMovie(id).get();
        typeMovie.setStatus(false);
        typeMovieRepository.save(typeMovie);
        response.put("type_movie", typeMovie.getTypeMovie());
        response.put("message", "Record deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
