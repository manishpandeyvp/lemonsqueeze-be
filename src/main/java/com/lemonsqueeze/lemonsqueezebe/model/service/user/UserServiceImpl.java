package com.lemonsqueeze.lemonsqueezebe.model.service.user;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lemonsqueeze.lemonsqueezebe.exception.EntityNotFoundException;
import com.lemonsqueeze.lemonsqueezebe.model.entity.User;
import com.lemonsqueeze.lemonsqueezebe.model.entity.generic.GenericResponse;
import com.lemonsqueeze.lemonsqueezebe.model.repository.UserRepository;
import com.lemonsqueeze.lemonsqueezebe.utility.GenericResponseUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public GenericResponse addUser(User user) {
        Optional<User> _user = userRepository.findById(user.getUsername());
        if (_user.isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return GenericResponseUtil.getGenericResponse(HttpStatus.CREATED, null, "User has been added successfully!");
        } else {
            return GenericResponseUtil.getGenericResponse(HttpStatus.BAD_REQUEST, null, "Username already exists!");
        }   
    }

    @Override
    public GenericResponse getUserDetailsByUserName(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("username", user.get().getUsername());
            hm.put("email_id", user.get().getEmailId());

            return GenericResponseUtil.getGenericResponse(HttpStatus.OK, hm, null);
        } else {
            return GenericResponseUtil.getGenericResponse(HttpStatus.NOT_FOUND, null, "User not found!");
        }
    }

    @Override
    public User getUserByUserName(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) return user.get();
        throw new EntityNotFoundException(username, User.class);
    }

    @Override
    public void updateUserByUserName(String username) {
        
    }

    @Override
    public void deleteUserByUserName(String username) {
        userRepository.deleteById(username);
    }

    // private GenericResponse getGenericResponse(HttpStatus status, Object object, String message) {
    //     GenericResponse response = new GenericResponse();
    //     response.setMeta(new Meta(status.toString(), message));
    //     response.setData(object);
    //     return response;
    // }
}
