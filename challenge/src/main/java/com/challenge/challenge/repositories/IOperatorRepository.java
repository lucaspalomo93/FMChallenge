package com.challenge.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.challenge.models.Operator;

@Repository
public interface IOperatorRepository extends JpaRepository<Operator, Long>{
    

    Operator findByUserName(String username);

    boolean existsByUserName(String username);
}
