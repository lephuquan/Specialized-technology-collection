package com.lpq.springsecurity.services.user;


import com.lpq.springsecurity.entities.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    @Getter
    private final Long userId;

    @Getter
    private final String email;

    private final String username;

    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    @Getter
    private final boolean firstLogin;

    public UserDetailsImpl(
            Long userId,
            String email,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean firstLogin
    ) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.firstLogin = firstLogin;
    }

    public static UserDetailsImpl build(Users user) {
        List<GrantedAuthority> authorityList = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getUserId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                authorityList,
                user.getFirstLogin());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
