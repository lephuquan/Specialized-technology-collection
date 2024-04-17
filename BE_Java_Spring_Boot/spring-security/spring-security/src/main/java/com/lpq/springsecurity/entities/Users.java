package com.ldcc.evsis.cms.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "account_expired")
    private Boolean accountExpired = Boolean.FALSE;

    @Column(name = "account_locked")
    private Boolean accountLocked = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonManagedReference
    private Set<Roles> roles = new HashSet<>();

    @Column(name = "reset_password_token", length = 100)
    private String resetPasswordToken;

    @Column(name = "reset_password_token_expired")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resetPasswordTokenExpired;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Token> tokens;

    @Column(name = "first_login")
    private Boolean firstLogin = Boolean.TRUE;
}
