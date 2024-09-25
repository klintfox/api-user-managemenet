package com.bci.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bci.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public User findByEmail(String email);

}