package com.istrategies.entity;

import com.istrategies.security.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "movies_sold")
public class MovieSold implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_sold_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, updatable = false)
    private Movie movie;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_purchase", nullable = false)
    private final Date datePurchase = new Date();
    @Column(nullable = false)
    private Boolean status;

    public MovieSold() {
    }

    public MovieSold(User user, Movie movie, Boolean status) {
        this.user = user;
        this.movie = movie;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
