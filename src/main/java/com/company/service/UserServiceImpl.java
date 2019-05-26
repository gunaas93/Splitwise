package com.company.service;

import com.company.data.User;
import com.company.data.UserGroup;
import com.company.exception.SplitwiseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by manishsharma on 10/03/18.
 */
@Service
public class UserServiceImpl implements UserService {

    Map<Integer, UserGroup> userGroupMap = new HashMap<>();

    Map<Integer, User> userMap = new HashMap<>();

    private static final AtomicInteger userCount = new AtomicInteger(0);

    private static final AtomicInteger groupCount = new AtomicInteger(0);

    @Override
    public User addUser(User user) {
        int userId = userCount.incrementAndGet();
        user.setId(userId);
        userMap.put(userId, user);
        return user;
    }

    @Override
    public UserGroup addGroup(UserGroup userGroup) {
        int groupId = groupCount.incrementAndGet();
        userGroup.setId(groupId);
        userGroupMap.put(groupId, userGroup);
        return userGroup;
    }

    @Override
    public void updateGroup(UserGroup userGroup) {
        if(!userGroupMap.containsKey(userGroup.getId())) {
            throw new SplitwiseException("User group does not exist");
        }
        userGroupMap.put(userGroup.getId(), userGroup);
    }

    @Override
    public User getUser(Integer userId) {
        return userMap.get(userId);
    }

    @Override
    public UserGroup getUserGroup(Integer userGroupId) {
        return userGroupMap.get(userGroupId);
    }
}
