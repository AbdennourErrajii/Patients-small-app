package com.example.patientspringmvc.repository;
import com.example.patientspringmvc.entities.Patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PatientRepo extends JpaRepository<Patient, Long>{

    Page<Patient> findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(String nom,String
            prenom, Pageable pageable);
}
