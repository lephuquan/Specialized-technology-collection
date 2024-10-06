package com.dev.lpq.demo_security_jwt.repository;

import com.dev.lpq.demo_security_jwt.entity.ERole.ERole;
import com.dev.lpq.demo_security_jwt.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
