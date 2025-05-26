package com.gtel.user_service.service;

import com.gtel.user_service.dto.UserPrincipal;
import com.gtel.user_service.entity.User;
import com.gtel.user_service.repositotry.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(new HashSet<>());
        }

        return userPrincipal;
    }
}
