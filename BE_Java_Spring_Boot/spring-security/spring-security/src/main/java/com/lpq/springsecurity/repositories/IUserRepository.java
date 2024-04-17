package com.ldcc.evsis.cms.repositories;

import com.ldcc.evsis.cms.entities.Users;
import com.ldcc.evsis.cms.entities.enums.ERole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByEmail(String email);

    Optional<Users> findUsersByEmailAndAccountLocked(String email, Boolean accountLocked);

    Optional<Users> findUsersByResetPasswordToken(String passwordToken);

    @Query(value = "SELECT DISTINCT u FROM Users u JOIN FETCH u.roles r" +
            " WHERE (:fullName IS NULL OR u.fullName LIKE CONCAT('%',:fullName,'%'))" +
            " AND (:email IS NULL OR u.email LIKE CONCAT('%',:email,'%'))" +
            " AND (:role IS NULL OR r.roleName = :role)" +
            " AND (:accountLocked IS NULL OR u.accountLocked = :accountLocked)",
            countQuery = " SELECT COUNT(u) FROM Users u LEFT JOIN u.roles r" +
                    " WHERE (:fullName IS NULL OR u.fullName LIKE CONCAT('%',:fullName,'%'))" +
                    " AND (:email IS NULL OR u.email LIKE CONCAT('%',:email,'%'))" +
                    " AND (:role IS NULL OR r.roleName = :role)" +
                    " AND (:accountLocked IS NULL OR u.accountLocked = :accountLocked)"
    )
    Page<Users> getUserWithFilter(
            String fullName,
            String email,
            ERole role,
            Boolean accountLocked,
            Pageable pageable
    );
}
