package com.istrategies.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_types_movies")
public class TypeMovie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_movie_id")
    private Integer id;
    @Column(name = "type_movie", length = 20, nullable = false)
    private String typeMovie;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private final Date createdAt = new Date();
    @Column(nullable = false)
    private boolean status;

    /** Relationship */

    /*@ManyToMany(mappedBy = "typeMovies")
    private List<Movie> movies;*/

    public TypeMovie() {
    }

    public TypeMovie(String typeMovie, boolean status) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
