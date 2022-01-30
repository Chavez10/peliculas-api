package com.istrategies.dto;

import com.istrategies.entity.TypeMovie;

import java.util.Date;
import java.util.List;

public class MovieDto {

    private Integer id;
    private String title;
    private String description;
    private String image;
    private double rentPrice;
    private double salesPrice;
    private int stock;
    private boolean availability;
    private Date createdAt;
    private boolean status;
    private int likes;
    private List<TypeMovie> typeMovies;

    public MovieDto() {
    }

    public MovieDto(String title, String description, String image, double rentPrice, double salesPrice, int stock, boolean availability, boolean status) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.rentPrice = rentPrice;
        this.salesPrice = salesPrice;
        this.stock = stock;
        this.availability = availability;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
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

    public List<TypeMovie> getTypeMovies() {
        return typeMovies;
    }

    public void setTypeMovies(List<TypeMovie> typeMovies) {
        this.typeMovies = typeMovies;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
