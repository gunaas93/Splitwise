package com.company.service;

import com.company.data.User;
import com.company.data.UserGroup;

/**
 * Created by manishsharma on 10/03/18.
 */
public interface UserService {

    User addUser(User user);

    UserGroup addGroup(UserGroup userGroup);

    void updateGroup(UserGroup userGroup);

    User getUser(Integer userId);

    UserGroup getUserGroup(Integer userGroupId);
}
