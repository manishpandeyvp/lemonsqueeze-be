package com.lemonsqueeze.lemonsqueezebe.model.service.Following;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lemonsqueeze.lemonsqueezebe.exception.EntityNotFoundException;
import com.lemonsqueeze.lemonsqueezebe.exception.FollowingItselfException;
import com.lemonsqueeze.lemonsqueezebe.model.entity.Following;
import com.lemonsqueeze.lemonsqueezebe.model.entity.User;
import com.lemonsqueeze.lemonsqueezebe.model.repository.FollowingRepository;
import com.lemonsqueeze.lemonsqueezebe.model.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FollowingServiceImpl implements FollowingService{

    FollowingRepository followingRepository;
    UserRepository userRepository;

    @Override
    public List<Following> getFollowingsOfUsername(String username) {
        Optional<User> user = userRepository.findById(username);
        Optional<List<Following>> following = followingRepository.findByUsername(unwrapUser(user, username));
        if (following.isPresent()) {
            return following.get();
        } else {
            throw new EntityNotFoundException(username, Following.class);
        }      
    }

    @Override
    public Following addFollowing(String username, Following following) {
        if (username.equals(following.getFollowing())) {
            throw new FollowingItselfException();
        }
        Optional<User> user = userRepository.findById(username);
        following.setUsername(unwrapUser(user, username));
        return followingRepository.save(following);
    }

    @Override
    public void removeFolliwing(String id) {
        followingRepository.deleteById(id);
    }

    static User unwrapUser(Optional<User> entity, String username) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(username, User.class);
    }
}
