package com.lpq.springsecurity.repositories;

import com.lpq.springsecurity.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Users,  Long> {



}
