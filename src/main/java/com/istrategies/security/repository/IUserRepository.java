package com.istrategies.security.repository;

import com.istrategies.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    @Query(value = "SELECT id_user FROM t_users WHERE username = ?1", nativeQuery = true)
    int getIdUser(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
