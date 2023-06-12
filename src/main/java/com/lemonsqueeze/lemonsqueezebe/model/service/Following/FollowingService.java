package com.lemonsqueeze.lemonsqueezebe.model.service.Following;

import java.util.List;

import com.lemonsqueeze.lemonsqueezebe.model.entity.Following;

public interface FollowingService {
    List<Following> getFollowingsOfUsername(String username);
    Following addFollowing(String username, Following following);
    void removeFolliwing(String id);
}
