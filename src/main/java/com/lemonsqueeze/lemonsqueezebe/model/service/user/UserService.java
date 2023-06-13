package com.lemonsqueeze.lemonsqueezebe.model.service.user;


import com.lemonsqueeze.lemonsqueezebe.model.entity.User;
import com.lemonsqueeze.lemonsqueezebe.model.entity.generic.GenericResponse;

public interface UserService {
    GenericResponse getUserByUserName(String username);
    void deleteUserByUserName(String username);
    void updateUserByUserName(String username);
    GenericResponse addUser(User user);
}
