package com.lifepill.possystem.security;

import com.lifepill.possystem.entity.Employer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class EmployerUserDetails implements UserDetails {

    private final Employer employer;

    public EmployerUserDetails(Employer employer) {
        this.employer = employer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(employer.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return employer.getEmployerPassword();
    }

    @Override
    public String getUsername() {
        return employer.getEmployerEmail();
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