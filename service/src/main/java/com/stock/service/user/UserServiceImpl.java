package com.stock.service.user;

import com.stock.service.core.AbstractDTO;
import com.stock.service.core.ResponseStatus;
import com.stock.service.jwtSecurity.JwtTokenUtil;
import com.stock.service.user.dto.UserDTO;
import com.stock.service.util.Constants;
import com.stock.service.util.Converter;
import com.stock.service.util.Updater;
import com.stock.service.util.Validator;
import com.stock.user.User;
import com.stock.user.role.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           JwtTokenUtil jwtTokenUtil
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public ResponseEntity<?> createUser(User user) {
        User oldUser = userRepository.findByUsername(user.getUsername());
        logger.info(user.getUsername());
        if (oldUser != null) {
            return new ResponseEntity<>(new ResponseStatus(false, "name already exist"),
                    HttpStatus.CREATED);
        }
        String ROLE = "ROLE_USER";
        Role role = roleRepository.findByRole(ROLE);
        if (role == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no role"),
                    HttpStatus.CREATED);

        }
        user.addRole(role);
        userRepository.save(user);
        return new ResponseEntity<>(new ResponseStatus(true, "user created"),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> editUser(UserDTO editedUser,
                                      HttpServletRequest request
    ) {
        if (!Validator.userDTOValidator.test(editedUser)) {
            return new ResponseEntity<>(new ResponseStatus(false, "bad request: no input value"),
                    HttpStatus.CREATED);
        }
        User updateUser = Converter.toUser().apply(editedUser);
        setIdToUser(request, updateUser);
        if (updateUser.getId() == null) {
            return new ResponseEntity<>(new ResponseStatus(false, "no user with such id"),
                    HttpStatus.CREATED);
        }
        User oldUser = userRepository.findByUsername(updateUser.getUsername());
        if (oldUser != null && !oldUser.getId().equals(updateUser.getId())) {
            return new ResponseEntity<>(new ResponseStatus(false, "user with such username already exist"),
                    HttpStatus.CREATED);
        }
        User refreshedUser = Updater.updateUser().apply(getUserByToken(request), updateUser);
        userRepository.save(refreshedUser);
        return new ResponseEntity<>(new ResponseStatus(true, "user edited"),
                HttpStatus.CREATED);
    }


    @Override
    public User getUserByToken(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        if (token != null) {
            String userName = jwtTokenUtil.getUsernameFromToken(token);
            if (userName != null) {
                User user = userRepository.findByUsername(userName);
                if (user != null) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public void setCreatorToDTO(HttpServletRequest request, AbstractDTO dto) {
        if (dto == null) {
            return;
        }
        User user = getUserByToken(request);
        if (user == null) {
            return;
        }
        dto.setCreatorId(user.getId());
    }

    public void setIdToUser(HttpServletRequest request, User user) {
        if (user == null) {
            return;
        }
        User authUser = getUserByToken(request);
        if (authUser == null) {
            return;
        }
        user.setId(authUser.getId());
    }
}
