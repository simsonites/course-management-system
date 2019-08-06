package com.softpager.cms.services;

import com.softpager.cms.entities.UserAccount;


public interface UserAccountService {
    UserAccount saveAccount(UserAccount account);

    UserAccount findByEmail(String userEmail);
}
