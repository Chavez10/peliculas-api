package com.istrategies.repository;

import com.istrategies.entity.RentedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface IRentedMovieRepository extends JpaRepository<RentedMovie, Integer> {

    @Query(value = "select return_date from rented_movie where rented_id = ?1", nativeQuery = true)
    Date findByReturnDate(Integer id);

    @Query(value = "SELECT * FROM rented_movie WHERE id_user = ?1", nativeQuery = true)
    List<RentedMovie> findAll(Integer id);
}
