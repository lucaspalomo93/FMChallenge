package com.challenge.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.challenge.models.Role;

public interface IRoleRepository extends JpaRepository<Role, Long>{
    
    Role findByName(String name);
}
