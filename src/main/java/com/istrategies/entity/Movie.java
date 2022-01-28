package com.istrategies.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(nullable = false)
    private String image;
    @Column(name = "rent_price", nullable = false)
    private double rentPrice;
    @Column(name = "sales_price", nullable = false)
    private double salesPrice;
    @Column(nullable = false)
    private int stock;
    @Column(nullable = false)
    private boolean availability;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private final Date createdAt = new Date();
    @Column(nullable = false)
    private boolean status;

    /** Relationship */
    @JoinTable(name = "movie_details", joinColumns = @JoinColumn(name = "movie_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "type_movie_id", nullable = false))
    @ManyToMany
    private List<TypeMovie> typeMovies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private List<MovieLog> logs;

    public Movie() {
    }

    public Movie(String title, String description, String image, double rentPrice, double salesPrice, int stock, boolean availability, boolean status) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void addTypeMovie(TypeMovie typeMovie){
        if (this.typeMovies == null){
            this.typeMovies = new ArrayList<>();
        }
        this.typeMovies.add(typeMovie);
    }

    public void setTypeMovies(List<TypeMovie> typeMovies) {
        this.typeMovies = typeMovies;
    }

    public List<TypeMovie> getTypeMovies() {
        return typeMovies;
    }

    public List<MovieLog> getLogs() {
        return logs;
    }

    public void setLogs(List<MovieLog> logs) {
        this.logs = logs;
    }
}
