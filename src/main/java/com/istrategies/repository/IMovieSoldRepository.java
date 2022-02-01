package com.istrategies.repository;

import com.istrategies.entity.MovieSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieSoldRepository extends JpaRepository<MovieSold, Integer> {

    @Query(value = "select * from movies_sold where id_user = ?1", nativeQuery = true)
    List<MovieSold> findAll(Integer idUser);
}
