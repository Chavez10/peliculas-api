package com.istrategies.dto;

public class ReturnDetailDto {

    private Integer id;
    private Integer rentedMovieId;
    private Integer userId;
    private Integer movieId;
    private String movie;
    private String user;
    private Boolean onTime;
    private double penalty;

    public ReturnDetailDto() {
    }

    public ReturnDetailDto(Integer rentedMovieId, Integer userId, Integer movieId, String movie, String user, Boolean onTime, double penalty) {
        this.rentedMovieId = rentedMovieId;
        this.userId = userId;
        this.movieId = movieId;
        this.movie = movie;
        this.user = user;
        this.onTime = onTime;
        this.penalty = penalty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRentedMovieId() {
        return rentedMovieId;
    }

    public void setRentedMovieId(Integer rentedMovieId) {
        this.rentedMovieId = rentedMovieId;
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

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
