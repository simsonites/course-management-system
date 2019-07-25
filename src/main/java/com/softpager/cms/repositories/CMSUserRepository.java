package com.softpager.cms.repositories;

import com.softpager.cms.abstracts.CMSUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMSUserRepository extends JpaRepository<CMSUser, Long> {
    CMSUser findByEmail(String email);
    void deleteByEmail(String email);
}
