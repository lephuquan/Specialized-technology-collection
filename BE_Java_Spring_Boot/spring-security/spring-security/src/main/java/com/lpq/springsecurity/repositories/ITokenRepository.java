package com.lpq.springsecurity.repositories;

import com.lpq.springsecurity.entities.Token;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);

    @Transactional
    void deleteByToken(String token);
}
