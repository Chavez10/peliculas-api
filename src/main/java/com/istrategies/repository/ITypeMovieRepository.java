package com.istrategies.repository;

import com.istrategies.entity.TypeMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeMovieRepository extends JpaRepository<TypeMovie, Integer> {
}
