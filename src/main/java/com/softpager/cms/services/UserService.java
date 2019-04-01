package com.softpager.cms.services;

import com.softpager.cms.abstracts.User;

public interface UserService {
    User getUser(String email);

    void save(User theUser);
}
