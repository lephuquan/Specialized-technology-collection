package com.lpq.springsecurity.repositories;

import com.lpq.springsecurity.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Users,  Long> {


    Optional<Users> findAccountByEmail(String email);
}
