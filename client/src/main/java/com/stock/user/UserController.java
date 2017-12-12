package com.stock.user;

import com.stock.service.jwtSecurity.JwtTokenUtil;
import com.stock.service.jwtSecurity.dto.JwtUser;
import com.stock.service.user.UserService;
import com.stock.service.user.dto.UserDTO;
import com.stock.service.util.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private final UserService userService;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    private String getUserName(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    ResponseEntity<?> createUser(@RequestBody  User user) {
        return userService.createUser(user);

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    ResponseEntity<?> editUser(@RequestBody UserDTO user, HttpServletRequest request) {
        return userService.editUser(user, request);
    }

}
