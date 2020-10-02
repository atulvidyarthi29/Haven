package com.start.haven.users.service;

import com.start.haven.security.JwtTokenUtil;
import com.start.haven.users.dao.HavenUserRepository;
import com.start.haven.users.model.AuthenticationRequest;
import com.start.haven.users.model.AuthenticationResponse;
import com.start.haven.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final HavenUserRepository havenUserRepository;
    private final HavenUserDetailService havenUserDetailService;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, HavenUserRepository havenUserRepository, HavenUserDetailService havenUserDetailService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.havenUserRepository = havenUserRepository;
        this.havenUserDetailService = havenUserDetailService;
    }

    public AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or Password", e);
        }
        final UserDetails userDetails = havenUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }


    public AuthenticationResponse registerNewUser(User newUser) throws Exception {
        havenUserRepository.save(newUser);
        return createAuthenticationToken(new AuthenticationRequest(
                newUser.getUsername(), newUser.getPassword()));
    }
}
