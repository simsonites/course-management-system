package com.softpager.cms.services;

import com.softpager.cms.entities.UserAccount;
import com.softpager.cms.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    @Autowired
    private UserAccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserAccount saveAccount(UserAccount account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account) ;
    }

    @Override
    public UserAccount findByEmail(String userEmail) {
        return accountRepository.findByEmail(userEmail);
    }
}
