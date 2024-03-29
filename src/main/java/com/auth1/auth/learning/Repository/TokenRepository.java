package com.auth1.auth.learning.Repository;

import com.auth1.auth.learning.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token , Long> {

}
