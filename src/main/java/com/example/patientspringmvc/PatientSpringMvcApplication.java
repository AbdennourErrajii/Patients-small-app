package com.example.patientspringmvc;

import com.example.patientspringmvc.entities.Patient;
import com.example.patientspringmvc.repository.PatientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientSpringMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(PatientRepo patientRepo){
        return args -> {
            //NoArgConstructor
           Patient p1=new Patient();
              p1.setNom("Hassan");
              p1.setPrenom("Khalid");
              p1.setDateNaissance(new Date());
              p1.setMalade(true);
              p1.setScore(75);


           //AllArgConstructor
           Patient p2=new Patient(null,"Rachid","RACHIDE",new Date(),90,false);

           //Builder
              Patient p3=Patient.builder()
                      .nom("Kamal")
                      .malade(true)
                      .dateNaissance(new Date())
                      .build();

            /*patientRepo.save(p1);
            patientRepo.save(p2);
            patientRepo.save(p3);*/

            /*List<Patient> patients=patientRepo.findAll();
            patients.forEach(p->{
                System.out.println(p.toString());
            });*/
        };
    }

}
