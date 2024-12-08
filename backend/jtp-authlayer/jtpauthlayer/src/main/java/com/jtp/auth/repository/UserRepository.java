package com.jtp.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtp.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
