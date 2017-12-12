package com.stock.security;

import com.stock.service.core.ResponseStatus;
import com.stock.service.user.UserRepository;
import com.stock.user.User;
import com.stock.user.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stock.service.jwtSecurity.JwtTokenUtil;
import com.stock.service.jwtSecurity.dto.JwtAuthenticationRequest;
import com.stock.service.jwtSecurity.dto.JwtAuthenticationResponse;

@RestController
public class AuthenticationRestController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;
    private UserRepository userRepository;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenUtil jwtTokenUtil,
                                        UserDetailsService userDetailsService,
                                        UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/adminAuth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationTokenForAdmin(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final User user = userRepository.findByUsername(authenticationRequest.getUsername());
        boolean flag = false;
        for (Role role : user.getRoles()) {
            if(role.getRole().equals("ROLE_ADMIN")) {
                flag = true;
                System.out.println("admin!");
                break;
            }
        }
        if (flag) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            // Return the token
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }
        System.out.println("not admin!");
        return new ResponseEntity<>(new ResponseStatus(false, "not admin"), HttpStatus.NOT_FOUND);
    }
}
