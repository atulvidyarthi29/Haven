package com.start.haven.security;

import com.start.haven.users.dao.HavenUserRepository;
import com.start.haven.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUtil {

    private final HavenUserRepository havenUserRepository;

    @Autowired
    public UserUtil(HavenUserRepository havenUserRepository) {
        this.havenUserRepository = havenUserRepository;
    }

    public Optional<User> loggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return havenUserRepository.findUserByUsername(username);
    }
}
