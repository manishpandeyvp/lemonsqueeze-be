package com.lemonsqueeze.lemonsqueezebe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemonsqueeze.lemonsqueezebe.model.entity.Recipe;
import com.lemonsqueeze.lemonsqueezebe.model.entity.User;
import com.lemonsqueeze.lemonsqueezebe.model.entity.generic.GenericResponse;
import com.lemonsqueeze.lemonsqueezebe.model.service.recipe.RecipeService;
import com.lemonsqueeze.lemonsqueezebe.model.service.user.UserService;
import com.lemonsqueeze.lemonsqueezebe.utility.StartesJuices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserService userService;
    RecipeService recipeService;
    
    @GetMapping("/{username}")
    public ResponseEntity<String> getUserByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUserName(username).getEmailId(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> addUser(@Valid @RequestBody User user) {
        GenericResponse genericResponse = userService.addUser(user);
        if (user.getUsername().equals("manishpandeyvp")) {
            for (Recipe recipe: StartesJuices.getStarterJuices()) {
                recipeService.addRecipe(recipe, user.getUsername());
            }
        }
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String username) {
        userService.deleteUserByUserName(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // TODO: this needs to be implemented
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
