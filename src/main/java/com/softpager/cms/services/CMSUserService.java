package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Role;

import java.util.List;
import java.util.Optional;


public interface CMSUserService {

    Optional<CMSUser> getUser(long theId);

    CMSUser save(CMSUser theUser);

    CMSUser findByEmail(String email);

    List findByRole(Role theRole);

}
