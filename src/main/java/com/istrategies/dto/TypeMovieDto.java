package com.istrategies.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TypeMovieDto {

    private Integer id;
    @NotEmpty(message = "The film type field must not be empty.")
    @NotNull(message = "The field film type must not be null.")
    private String typeMovie;
    private Date createdAt;
    @NotNull(message = "The status field must not be null.")
    private boolean status;

    public TypeMovieDto() {
    }

    public TypeMovieDto(String typeMovie, boolean status) {
        this.typeMovie = typeMovie;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeMovie() {
        return typeMovie;
    }

    public void setTypeMovie(String typeMovie) {
        this.typeMovie = typeMovie;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
