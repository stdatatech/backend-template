package com.stdata.backend.security;

import com.stdata.backend.entity.ApiToken;
import com.stdata.backend.entity.User;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private User user;
    private ApiToken apiToken;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public UserDetailsImpl(User user, ApiToken apiToken) {
        this.user = user;
        this.apiToken = apiToken;
    }

    public User getUser() {
        return user;
    }

    public ApiToken getApiToken() {
        return apiToken;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.isEnable();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
