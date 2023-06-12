package com.lemonsqueeze.lemonsqueezebe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemonsqueeze.lemonsqueezebe.model.entity.Recipe;
import com.lemonsqueeze.lemonsqueezebe.model.service.recipe.RecipeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/recipe")
@AllArgsConstructor
public class RecipeController {

    RecipeService recipeService;
    
    @PostMapping("/{username}")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe, @PathVariable String username) {
        recipeService.addRecipe(recipe, username);
        return new ResponseEntity<Recipe>(HttpStatus.CREATED);
    }

    @GetMapping("/{username}/get-all")
    public ResponseEntity<List<Recipe>> getAllRecipesByUser(@PathVariable String username) {
        return new ResponseEntity<List<Recipe>>(recipeService.getAllRecipeOfUser(username), HttpStatus.OK);
    }
}
