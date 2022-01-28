package com.istrategies.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "movies_logs")
public class MovieLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, updatable = false)
    private Movie movie;
    @Column(nullable = false)
    private String title;
    @Column(name = "rent_price", nullable = false)
    private double rentPrice;
    @Column(name = "sales_price", nullable = false)
    private double salesPrice;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private final Date updatedAt = new Date();

    public MovieLog() {
    }

    public MovieLog(Movie movie, String title, double rentPrice, double salesPrice) {
        this.movie = movie;
        this.title = title;
        this.rentPrice = rentPrice;
        this.salesPrice = salesPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
