package com.lemonsqueeze.lemonsqueezebe.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lemonsqueeze.lemonsqueezebe.model.entity.Following;
import com.lemonsqueeze.lemonsqueezebe.model.entity.User;

public interface FollowingRepository extends CrudRepository<Following, String>{

    Optional<List<Following>> findByUsername(User username);
    
}
