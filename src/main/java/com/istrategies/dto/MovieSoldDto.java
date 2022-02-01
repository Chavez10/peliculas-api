package com.istrategies.dto;

import com.istrategies.entity.Movie;
import com.istrategies.security.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class MovieSoldDto {

    @NotNull
    private Integer idUser;
    @NotNull
    private Integer idMovie;
    @NotNull
    @NotEmpty
    private String user;
    @NotNull
    @NotEmpty
    private String movie;
    private Date datePurchase;
    @NotNull
    private Boolean status;


    public MovieSoldDto() {
    }

    public MovieSoldDto(String user, String movie, Boolean status) {
        this.user = user;
        this.movie = movie;
        this.status = status;
    }


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
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

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
