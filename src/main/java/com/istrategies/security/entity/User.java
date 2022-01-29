package com.istrategies.security.entity;

import com.istrategies.entity.MovieSold;
import com.istrategies.entity.RentedMovie;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;
    @Column(nullable = false, length = 10, unique = true)
    private String username;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private final Date createdAt = new Date();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "id_user"),
    inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MovieSold> movie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<RentedMovie> rentedMovies;

    public User() {
    }

    public User(String username, String email, String password, Boolean status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<MovieSold> getMovie() {
        return movie;
    }

    public void setMovie(List<MovieSold> movie) {
        this.movie = movie;
    }

    public List<RentedMovie> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(List<RentedMovie> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }
}
