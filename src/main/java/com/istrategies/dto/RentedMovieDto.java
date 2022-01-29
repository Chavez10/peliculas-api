package com.istrategies.dto;

import java.util.Date;

public class RentedMovieDto {

    private Integer rentedId;
    private Integer userId;
    private Integer movieId;
    private String user;
    private String movie;
    private Date rentDate;
    private Date returnDate;
    private Boolean status;

    public RentedMovieDto() {
    }

    public RentedMovieDto(Integer userId, Integer movieId, String user, String movie, Date rentDate, Date returnDate, Boolean status) {
        this.userId = userId;
        this.movieId = movieId;
        this.user = user;
        this.movie = movie;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Integer getRentedId() {
        return rentedId;
    }

    public void setRentedId(Integer rentedId) {
        this.rentedId = rentedId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
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
