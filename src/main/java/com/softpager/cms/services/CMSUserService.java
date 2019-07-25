package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;

import java.util.Optional;


public interface CMSUserService {

    Optional<CMSUser> getUser(long theId);

    void save(CMSUser theUser);

    void deleteByEmail(String email);

    CMSUser findByEmail(String email);
}
