package com.istrategies.impl;

import com.istrategies.dto.RentedMovieDto;
import com.istrategies.dto.ReturnDetailDto;
import com.istrategies.entity.RentedMovie;
import com.istrategies.entity.ReturnDetail;
import com.istrategies.repository.IMovieRepository;
import com.istrategies.repository.IRentedMovieRepository;
import com.istrategies.repository.IReturnDetailRepository;
import com.istrategies.service.IMovieService;
import com.istrategies.service.IReturnDetailService;
import com.istrategies.settings.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReturnDetailImpl implements IReturnDetailService {

    @Autowired
    private IReturnDetailRepository returnDetailRepository;
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IRentedMovieRepository rentedMovieRepository;

    @Override
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<ReturnDetailDto> returnDetail =
                returnDetailRepository.findAll()
                        .stream()
                        .map(returnDt -> {
                            ReturnDetailDto dto = new ReturnDetailDto();
                            dto.setId(returnDt.getId());
                            dto.setUserId(returnDt.getRentedMovie().getUser().getId());
                            dto.setRentedMovieId(returnDt.getRentedMovie().getId());
                            dto.setUser(returnDt.getRentedMovie().getUser().getUsername());
                            dto.setMovieId(returnDt.getRentedMovie().getMovie().getId());
                            dto.setMovie(returnDt.getRentedMovie().getMovie().getTitle());
                            dto.setOnTime(returnDt.getOnTime());
                            dto.setPenalty(returnDt.getPenalty());

                            return dto;
                        }).collect(Collectors.toList());

        if (returnDetail.size() > 0){
            response.put("rent_movie", returnDetail);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "No records yet.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public Optional<ReturnDetail> getMovie(Integer id) {
        Optional<ReturnDetail> returnDetail = returnDetailRepository.findById(id);
        if (returnDetail.isEmpty()){
            throw new NotFoundException("The requested resource does not exist.");
        }
        return returnDetail;
    }

    @Override
    public ResponseEntity<?> getByIdMovie(Integer id) {
        Map<String, Object> response = new HashMap<>();
        ReturnDetailDto dto = new ReturnDetailDto();
        ReturnDetail returnDt = this.getMovie(id).get();

        dto.setId(returnDt.getId());
        dto.setRentedMovieId(returnDt.getRentedMovie().getId());
        dto.setUserId(returnDt.getRentedMovie().getUser().getId());
        dto.setUser(returnDt.getRentedMovie().getUser().getUsername());
        dto.setMovieId(returnDt.getRentedMovie().getMovie().getId());
        dto.setMovie(returnDt.getRentedMovie().getMovie().getTitle());
        dto.setOnTime(returnDt.getOnTime());
        dto.setPenalty(returnDt.getPenalty());

        response.put("return_movie", dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveRentMovie(ReturnDetail movie) {
        Map<String, Object> response = new HashMap<>();
        ReturnDetail returnDetail = null;
        Integer rentId = movie.getRentedMovie().getId();
        Date dateNow = new Date();
        Date dateReturn = rentedMovieRepository.findByReturnDate(rentId);
        int movieId = movie.getRentedMovie().getMovie().getId();
        double priceMovie = movieRepository.findBySalesPrice(movieId);
        int stock = movieRepository.findByStock(movieId) + 1;

        movie.setOnTime(true);
        movie.setPenalty(0.00);
        movie.setStatus(true);

        if (dateNow.after(dateReturn)){
            movie.setOnTime(false);
            movie.setPenalty(priceMovie+5.5);
        }

        returnDetail = returnDetailRepository.save(movie);
        movieService.updateStock(movieId, stock);
        response.put("movie", movie.getRentedMovie().getMovie().getTitle());
        response.put("message", "Movie returned.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<?> deleteRentMovie(Integer id) {
        Map<String, Object> response = new HashMap<>();
        ReturnDetail returnDetail = this.getMovie(id).get();
        returnDetail.setStatus(false);

        returnDetailRepository.save(returnDetail);
        response.put("return_movie", returnDetail.getRentedMovie().getMovie().getTitle());
        response.put("message", "Record deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
