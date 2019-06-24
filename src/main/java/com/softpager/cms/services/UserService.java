package com.softpager.cms.services;

import com.softpager.cms.abstracts.AbstractUser;

public interface UserService {
    AbstractUser getUser(String email);

    void save(AbstractUser theUser);

    void delete(String email);
}
