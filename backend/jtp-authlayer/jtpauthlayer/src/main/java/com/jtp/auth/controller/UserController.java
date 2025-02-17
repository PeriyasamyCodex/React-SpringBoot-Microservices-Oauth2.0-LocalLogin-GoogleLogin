package com.jtp.auth.controller;

import com.jtp.auth.exception.ResourceNotFoundException;
import com.jtp.auth.entity.User;
import com.jtp.auth.repository.UserRepository;
import com.jtp.auth.security.oauth2.user.CurrentUser;
import com.jtp.auth.security.oauth2.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
