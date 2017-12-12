package com.stock.service.jwtSecurity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private final String username;
    private final String name;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> roles;
    private final Date lastPasswordResetDate;

    public JwtUser(
            String username,
            String name,
            String email,
            String password,
            Collection<? extends GrantedAuthority> roles,
            Date lastPasswordResetDate
    ) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.lastPasswordResetDate = lastPasswordResetDate;
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

    public String getName() {return name;}

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

}
