package com.lpq.springsecurity.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lpq.springsecurity.entities.enums.AccountStatus;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_users")
public class Users extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "full_name")
    private String fullName = null;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonManagedReference
    private Set<Roles> roles = new HashSet<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Token> tokens;

    @Column(name = "first_login")
    private Boolean firstLogin = Boolean.TRUE;

//    @Column(name = "reset_password_token", length = 100)
//    private String resetPasswordToken;

//    @Column(name = "account_expired")
//    private Boolean accountExpired = Boolean.FALSE;

//    @Column(name = "reset_password_token_expired")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date resetPasswordTokenExpired;
}
