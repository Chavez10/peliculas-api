package com.istrategies.impl;

import com.istrategies.dto.MovieLogDto;
import com.istrategies.entity.MovieLog;
import com.istrategies.repository.IMovieLogRepository;
import com.istrategies.service.IMovieLogService;
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
public class MovieLogImpl implements IMovieLogService {

    private final IMovieLogRepository movieLogRepository;

    public MovieLogImpl(IMovieLogRepository movieLogRepository) {
        this.movieLogRepository = movieLogRepository;
    }

    @Override
    public ResponseEntity<?> findAllLogs() {
        Map<String, Object> response = new HashMap<>();
        List<MovieLogDto> logs =
                movieLogRepository
                        .findAll()
                        .stream()
                        .map(movieLog -> {
                            MovieLogDto dto = new MovieLogDto();
                            dto.setMovieId(movieLog.getMovie().getId());
                            dto.setTitle(movieLog.getTitle());
                            dto.setRentPrice(movieLog.getRentPrice());
                            dto.setSalesPrice(movieLog.getSalesPrice());
                            dto.setUpdatedAt(movieLog.getUpdatedAt());

                            return dto;
                        }).collect(Collectors.toList());
        if (logs.size() > 0){
            response.put("logs", logs);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "No records yet.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public Optional<MovieLog> getLog(Integer id) {
        Optional<MovieLog> log = movieLogRepository.findById(id);
        if (log.isEmpty()){
            throw new NotFoundException("The requested resource does not exist.");
        }
        return log;
    }

    @Override
    public ResponseEntity<?> getByIdLog(Integer id) {
        Map<String, Object> response = new HashMap<>();
        MovieLogDto dto = new MovieLogDto();
        MovieLog movieLog = this.getLog(id).get();

        dto.setMovieId(movieLog.getMovie().getId());
        dto.setTitle(movieLog.getTitle());
        dto.setRentPrice(movieLog.getRentPrice());
        dto.setSalesPrice(movieLog.getSalesPrice());
        dto.setUpdatedAt(movieLog.getUpdatedAt());

        response.put("logs", dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public MovieLog saveLog(MovieLog log) {
        return movieLogRepository.save(log);
    }
}
