package com.istrategies.repository;

import com.istrategies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {


    List<Movie> findByAvailability(boolean availability);

    @Query(value = "select * from t_movies order by title asc", nativeQuery = true)
    List<Movie> findAllTitle();

    @Query(value = "select * from t_movies order by likes desc", nativeQuery = true)
    List<Movie> findAllLikes();

    @Query(value = "SELECT stock FROM t_movies WHERE movie_id = ?1", nativeQuery = true)
    int findByStock(Integer id);

    @Query(value = "SELECT sales_price FROM t_movies WHERE movie_id = ?1", nativeQuery = true)
    double findBySalesPrice(Integer id);

    @Query(value = "SELECT likes FROM t_movies WHERE movie_id = ?1", nativeQuery = true)
    Integer findByLikes(Integer id);

}
