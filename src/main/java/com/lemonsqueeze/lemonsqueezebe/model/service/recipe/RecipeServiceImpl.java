package com.lemonsqueeze.lemonsqueezebe.model.service.recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lemonsqueeze.lemonsqueezebe.exception.EntityNotFoundException;
import com.lemonsqueeze.lemonsqueezebe.model.entity.Recipe;
import com.lemonsqueeze.lemonsqueezebe.model.entity.User;
import com.lemonsqueeze.lemonsqueezebe.model.repository.RecipeRepository;
import com.lemonsqueeze.lemonsqueezebe.model.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService{

    RecipeRepository recipeRepository;
    UserRepository userRepository;

    @Override
    public List<Recipe> getAllRecipeOfUser(String username) {
        Optional<User> user = userRepository.findById(username);
        Optional<List<Recipe>> recipes = recipeRepository.findByPostedBy(unwrapUser(user, username));
        if (recipes.isPresent()) {
            return recipes.get();
        } else {
            throw new EntityNotFoundException(username, Recipe.class);
        }
    }

    @Override
    public Recipe addRecipe(Recipe recipe, String username) {
        Optional<User> user = userRepository.findById(username);
        recipe.setPostedBy(unwrapUser(user, username));
        recipe.setAuthor(unwrapUser(user, username).getUsername());
        return recipeRepository.save(recipe);
    }   

    static User unwrapUser(Optional<User> entity, String username) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(username, User.class);
    }
    
}
