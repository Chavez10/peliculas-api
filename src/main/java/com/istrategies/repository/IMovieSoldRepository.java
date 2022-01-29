package com.istrategies.repository;

import com.istrategies.entity.MovieSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieSoldRepository extends JpaRepository<MovieSold, Integer> {
}
