package com.istrategies.repository;

import com.istrategies.entity.MovieLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieLogRepository extends JpaRepository<MovieLog, Integer> {
}
