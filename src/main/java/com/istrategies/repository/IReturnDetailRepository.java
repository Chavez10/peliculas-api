package com.istrategies.repository;

import com.istrategies.entity.ReturnDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReturnDetailRepository extends JpaRepository<ReturnDetail, Integer> {
}
