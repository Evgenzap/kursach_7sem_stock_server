package com.stock.service.user;

import com.stock.service.core.AbstractDTO;
import com.stock.service.user.dto.UserDTO;
import com.stock.user.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ResponseEntity<?> createUser(User user);
    ResponseEntity<?> editUser(UserDTO user, HttpServletRequest request);
    User getUserByToken(HttpServletRequest request);
    void setCreatorToDTO(HttpServletRequest request, AbstractDTO dto);
}
