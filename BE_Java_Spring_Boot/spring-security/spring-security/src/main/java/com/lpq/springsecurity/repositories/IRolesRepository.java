package com.lpq.springsecurity.repositories;

import com.lpq.springsecurity.entities.Roles;
import com.lpq.springsecurity.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findRolesByRoleName(ERole role);
}
