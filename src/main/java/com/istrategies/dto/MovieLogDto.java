package com.istrategies.dto;

import java.util.Date;

public class MovieLogDto {

    private Integer movieId;
    private String title;
    private double rentPrice;
    private double salesPrice;
    private Date updatedAt;

    public MovieLogDto() {
    }

    public MovieLogDto(Integer movieId, String title, double rentPrice, double salesPrice, Date updatedAt) {
        this.movieId = movieId;
        this.title = title;
        this.rentPrice = rentPrice;
        this.salesPrice = salesPrice;
        this.updatedAt = updatedAt;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
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

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
