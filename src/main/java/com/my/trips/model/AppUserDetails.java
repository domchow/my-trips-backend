package com.my.trips.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class AppUserDetails implements UserDetails {

    private final String userName;
    private final String password;
    private final boolean active;
    private final List<GrantedAuthority> authorities;

    public AppUserDetails(User user) {
        this.userName = user.getName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
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
        return userName;
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
        return active;
    }
}

