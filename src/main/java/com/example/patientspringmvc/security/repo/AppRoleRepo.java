package com.example.patientspringmvc.security.repo;

import com.example.patientspringmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole,String> {
}
