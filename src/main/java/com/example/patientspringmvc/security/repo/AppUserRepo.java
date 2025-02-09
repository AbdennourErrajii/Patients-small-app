package com.example.patientspringmvc.security.repo;

import com.example.patientspringmvc.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser,String>{
    AppUser findByUsername(String username);
}
