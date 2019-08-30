package com.softpager.cms.services;

import com.softpager.cms.entities.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Admin> getAdmins();

    void saveAdmin(Admin theAdmin);

    Optional<Admin> getAdmin(String email);

    void delete(String email);
}
