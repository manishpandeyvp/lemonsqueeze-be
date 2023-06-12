package com.lemonsqueeze.lemonsqueezebe.model.service.recipe;

import java.util.List;

import com.lemonsqueeze.lemonsqueezebe.model.entity.Recipe;

public interface RecipeService {
    List<Recipe> getAllRecipeOfUser(String username);
    Recipe addRecipe(Recipe recipe, String username);
}
