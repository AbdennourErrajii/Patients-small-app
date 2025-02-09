package com.example.patientspringmvc.security.service;

import com.example.patientspringmvc.security.entities.AppRole;
import com.example.patientspringmvc.security.entities.AppUser;
import com.example.patientspringmvc.security.repo.AppRoleRepo;
import com.example.patientspringmvc.security.repo.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepo appUserRepo;
    private AppRoleRepo appRoleRepo;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password,String email, String confirmedPassword) {
        AppUser appUser = appUserRepo.findByUsername(username);
        if(appUser != null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedAppUser=appUserRepo.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole =appRoleRepo.findById(role).orElse(null);
        if(appRole!=null) throw new RuntimeException("This role already exist");
        appRole=AppRole.builder().role(role).build();
        return appRoleRepo.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
      AppUser appUser=appUserRepo.findByUsername(username);
      AppRole appRole =appRoleRepo.findById(role).get();
      appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser=appUserRepo.findByUsername(username);
        AppRole appRole =appRoleRepo.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepo.findByUsername(username);
    }
}
