package com.auth1.auth.learning.Repository;

import com.auth1.auth.learning.Models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {


    Optional<user> findByEmail(String email);
}