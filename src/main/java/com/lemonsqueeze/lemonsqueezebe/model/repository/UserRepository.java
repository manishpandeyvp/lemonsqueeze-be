package com.lemonsqueeze.lemonsqueezebe.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.lemonsqueeze.lemonsqueezebe.model.entity.User;


public interface UserRepository extends CrudRepository<User, String> {

}
