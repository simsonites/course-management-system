package com.softpager.cms.repositories;

import com.softpager.cms.abstracts.CMSUser;
import com.softpager.cms.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CMSUserRepository extends JpaRepository<CMSUser, Long> {
       List findByRole(Role theRole);


}
