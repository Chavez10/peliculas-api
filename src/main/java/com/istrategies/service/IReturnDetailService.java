package com.istrategies.service;


import com.istrategies.entity.ReturnDetail;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IReturnDetailService {

    ResponseEntity<?> findAll();
    Optional<ReturnDetail> getMovie(Integer id);
    ResponseEntity<?> getByIdMovie(Integer id);
    ResponseEntity<?> saveRentMovie(ReturnDetail movie);
    ResponseEntity<?> deleteRentMovie(Integer id);
}
