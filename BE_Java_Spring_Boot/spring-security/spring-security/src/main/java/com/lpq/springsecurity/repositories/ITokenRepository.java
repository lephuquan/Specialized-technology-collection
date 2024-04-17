package com.lpq.springsecurity.repositories;

import com.lpq.springsecurity.entities.Token;
import com.lpq.springsecurity.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ITokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);

    @Transactional
    void deleteByToken(String token);

    @Transactional
    void deleteByUsers(Users users);
}
