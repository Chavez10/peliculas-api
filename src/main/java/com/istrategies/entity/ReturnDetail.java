package com.istrategies.entity;

import javax.persistence.*;


@Entity
@Table(name = "return_detail")
public class ReturnDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "rented_id", referencedColumnName = "rented_id")
    private RentedMovie rentedMovie;
    @Column(name = "on_time", nullable = false)
    private Boolean onTime;
    private double penalty;
    @Column(nullable = false)
    private Boolean status;

    public ReturnDetail() {
    }

    public ReturnDetail(RentedMovie rentedMovie, Boolean onTime, double penalty, Boolean status) {
        this.rentedMovie = rentedMovie;
        this.onTime = onTime;
        this.penalty = penalty;
        this.status = status;
    }

    public ReturnDetail(RentedMovie rentedMovie) {
        this.rentedMovie = rentedMovie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RentedMovie getRentedMovie() {
        return rentedMovie;
    }

    public void setRentedMovie(RentedMovie rentedMovie) {
        this.rentedMovie = rentedMovie;
    }

    public Boolean getOnTime() {
        return onTime;
    }

    public void setOnTime(Boolean onTime) {
        this.onTime = onTime;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
