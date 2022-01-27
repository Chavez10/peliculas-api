package com.istrategies.security.entity;

import com.istrategies.security.enums.NameRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id;
    @Column(name = "name_role", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private NameRole nameRole;
    @Column(nullable = false)
    private Boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private final Date createdAt = new Date();

    public Role() {
    }

    public Role(NameRole nameRole, Boolean status) {
        this.nameRole = nameRole;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NameRole getNameRole() {
        return nameRole;
    }

    public void setNameRole(NameRole nameRole) {
        this.nameRole = nameRole;
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
}
