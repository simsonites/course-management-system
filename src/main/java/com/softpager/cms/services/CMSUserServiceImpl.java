package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.entities.Role;
import com.softpager.cms.repositories.CMSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CMSUserServiceImpl implements CMSUserService {


    private Course course = new Course();


    @Autowired
    private CMSUserRepository cmsUserRepository;


    public Optional<CMSUser> getUser(long theId) {
        return cmsUserRepository.findById(theId);
    }

    @Override
    public CMSUser save(CMSUser theUser) {
        cmsUserRepository.save(theUser);
        return theUser;
    }

    @Override
    public CMSUser findByEmail(String email) {
        return null;
    }

    @Override
    public List findByRole(Role theRole) {
        return cmsUserRepository.findByRole(theRole);
    }

}
