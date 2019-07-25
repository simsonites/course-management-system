package com.softpager.cms.services;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Course;
import com.softpager.cms.repositories.CMSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(CMSUser theUser) {
        cmsUserRepository.save(theUser);

    }

    @Override
    public void deleteByEmail(String email) {
        course.removeUserFromCourse(cmsUserRepository.findByEmail(email));
        cmsUserRepository.deleteByEmail(email);
    }

    @Override
    public CMSUser findByEmail(String email) {
        return  cmsUserRepository.findByEmail(email);
    }


}
