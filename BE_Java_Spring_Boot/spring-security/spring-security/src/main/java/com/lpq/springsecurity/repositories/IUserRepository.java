package com.lpq.springsecurity.repositories;

import com.lpq.springsecurity.entities.Users;
import com.lpq.springsecurity.entities.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByEmail(String email);

    Optional<Users> findUsersByEmailAndStatus(String email, AccountStatus accountLocked);

}
