package com.stdata.backend.service;

import com.stdata.backend.entity.ApiToken;
import com.stdata.backend.entity.User;
import com.stdata.backend.repository.ApiTokenRepository;
import com.stdata.backend.repository.UserRepository;
import com.stdata.backend.security.UserDetailsImpl;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApiTokenRepository apiTokenRepository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setAdmin(true);
        user.setUsername("admin");
        user.setNickName("Admin");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        Optional<User> optionalUser = userRepository.findByUsername("admin");
        if (!optionalUser.isPresent()) {
            userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findByUsername(username);
        return userOptional.map(UserDetailsImpl::new).orElse(null);
    }

    public UserDetails loadUserByApiToken(String token) throws UsernameNotFoundException {
        Optional<ApiToken> optionalApiToken = this.apiTokenRepository.findByToken(token);
        return optionalApiToken.map(apiToken -> new UserDetailsImpl(apiToken.getUser(), apiToken))
            .orElse(null);
    }
}
