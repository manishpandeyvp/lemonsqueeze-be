package com.lemonsqueeze.lemonsqueezebe.model.service.user;


import com.lemonsqueeze.lemonsqueezebe.model.entity.User;

public interface UserService {
    User getUserByUserName(String username);
    void deleteUserByUserName(String username);
    void updateUserByUserName(String username);
    User addUser(User user);
}
