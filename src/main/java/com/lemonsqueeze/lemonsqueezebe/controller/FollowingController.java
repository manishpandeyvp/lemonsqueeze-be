package com.lemonsqueeze.lemonsqueezebe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemonsqueeze.lemonsqueezebe.model.entity.Following;
import com.lemonsqueeze.lemonsqueezebe.model.service.Following.FollowingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/following")
public class FollowingController {
    
    FollowingService followingService;

    @GetMapping("/{username}")
    public ResponseEntity<List<Following>> getAllFollowings(@PathVariable String username) {
        return new ResponseEntity<>(followingService.getFollowingsOfUsername(username), HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity<Following> addFollowingToUser(@PathVariable String username, @RequestBody Following following) {
        followingService.addFollowing(username, following);
        return new ResponseEntity<Following>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{followerId}")
    public ResponseEntity<Following> deleteFollowingById(@PathVariable String followerId) {
        followingService.removeFolliwing(followerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
