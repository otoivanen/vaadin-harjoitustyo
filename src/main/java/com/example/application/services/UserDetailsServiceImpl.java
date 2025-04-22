package com.example.application.services;

import com.example.application.data.Role;
import com.example.application.data.User;
import com.example.application.data.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        initDefaultUsers();
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream().map(
                role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());
    }

    private void initDefaultUsers() {
        if (userRepository.count() == 0) { // Only if no users present
            User admin = new User();
            admin.setUsername("admin");
            admin.setName("Administrator");
            admin.setHashedPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of(Role.ADMIN));

            User user = new User();
            user.setUsername("user");
            user.setName("Teuvo Terävä");
            user.setHashedPassword(passwordEncoder.encode("user"));
            user.setRoles(Set.of(Role.USER));

            userRepository.save(admin);
            userRepository.save(user);
        }}

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "No user present with username: "+username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(), getAuthorities(user));
    }
}
