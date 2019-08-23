package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Role;

import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<CMSUser> getUser(long theId);

    void save(CMSUser theUser);

    void deleteByEmail(String email);

    CMSUser findByEmail(String email);

    List findByRoles(Role theRole);

}