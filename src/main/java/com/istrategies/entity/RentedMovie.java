package com.istrategies.entity;

import com.istrategies.security.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rented_movie")
public class RentedMovie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rented_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, updatable = false)
    private Movie movie;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "rent_date")
    private Date rentDate;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "return_date")
    private Date returnDate;
    @Column(nullable = false)
    private Boolean status;

    /** Relationship */


    @OneToOne(mappedBy = "rentedMovie")
    private ReturnDetail returnDetail;

    public RentedMovie() {
    }

    public RentedMovie(User user, Movie movie, Date rentDate, Date returnDate, Boolean status) {
        this.user = user;
        this.movie = movie;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
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

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
