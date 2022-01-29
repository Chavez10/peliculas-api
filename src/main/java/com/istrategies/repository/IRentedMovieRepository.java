package com.istrategies.repository;

import com.istrategies.entity.RentedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface IRentedMovieRepository extends JpaRepository<RentedMovie, Integer> {

    @Query(value = "select return_date from rented_movie where rented_id = ?1", nativeQuery = true)
    Date findByReturnDate(Integer id);
}
