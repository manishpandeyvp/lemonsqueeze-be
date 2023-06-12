package com.lemonsqueeze.lemonsqueezebe.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lemonsqueeze.lemonsqueezebe.model.entity.Recipe;
import com.lemonsqueeze.lemonsqueezebe.model.entity.User;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
    Optional<List<Recipe>> findByPostedBy(User postedBy);
}
