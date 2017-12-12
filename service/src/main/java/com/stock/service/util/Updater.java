package com.stock.service.util;

import com.stock.user.User;

import java.util.function.BinaryOperator;

public class Updater {
    public static BinaryOperator<User> updateUser() {
        return (old, fresh) -> {
            User user = new User();
            user.setId(old.getId());
            user.setUsername(fresh.getUsername());
            user.setName(fresh.getName());
            user.setEmail(fresh.getEmail());
            user.setPassword(fresh.getPassword());
            return user;
        };
    }
}
