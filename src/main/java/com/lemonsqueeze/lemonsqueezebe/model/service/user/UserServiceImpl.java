package com.lemonsqueeze.lemonsqueezebe.model.service.user;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lemonsqueeze.lemonsqueezebe.exception.EntityNotFoundException;
import com.lemonsqueeze.lemonsqueezebe.exception.UserAlreadyExistsException;
import com.lemonsqueeze.lemonsqueezebe.model.entity.User;
import com.lemonsqueeze.lemonsqueezebe.model.entity.generic.GenericResponse;
import com.lemonsqueeze.lemonsqueezebe.model.entity.generic.Meta;
import com.lemonsqueeze.lemonsqueezebe.model.repository.UserRepository;

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
            return getGenericResponse(HttpStatus.CREATED, null, "User has been added successfully!");
        } else {
            throw new UserAlreadyExistsException(user.getUsername());
        }   
    }

    @Override
    public User getUserByUserName(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            // JsonObject jsonObject = new JsonObject();
            // jsonObject.addProperty("username", user.getUsername());
            // jsonObject.addProperty("emailId", user.getEmailId());
            return user.get();
        } else {
            throw new EntityNotFoundException(username, User.class);
        }
    }

    @Override
    public void updateUserByUserName(String username) {
        
    }

    @Override
    public void deleteUserByUserName(String username) {
        userRepository.deleteById(username);
    }

    private GenericResponse getGenericResponse(HttpStatus status, Object object, String message) {
        GenericResponse response = new GenericResponse();
        response.setMeta(new Meta(status.toString(), message));
        response.setData(object);
        return response;
    }
    
}
