package com.multagent.decisionengine.security;

import com.multagent.decisionengine.entity.User;
import com.multagent.decisionengine.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found: " + username));

        Set<GrantedAuthority> authorities = new HashSet<>();

        // Get all permissions from the user's roles
        user.getRoles().forEach(role -> {
            role.getPermissions().forEach(permission -> {
                // Prefix with ROLE_ as required by Spring Security's hasRole
                authorities.add(new SimpleGrantedAuthority("ROLE_" + permission.getName()));
            });
        });

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .accountEnabled(user.isEnabled())
                .build();
    }
}