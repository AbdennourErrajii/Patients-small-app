package com.example.patientspringmvc;

import com.example.patientspringmvc.entities.Patient;
import com.example.patientspringmvc.repository.PatientRepo;
import com.example.patientspringmvc.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientSpringMvcApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
                      .prenom("Kamal")
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

    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        return args -> {
            if(!jdbcUserDetailsManager.userExists("admin11")){
                jdbcUserDetailsManager.createUser(User.withUsername("admin11").password(passwordEncoder().encode("1234")).roles("ADMIN","USER").build());
            }
            if(!jdbcUserDetailsManager.userExists("user11")){
                jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder().encode("1234")).roles("USER").build());
            }
            if(!jdbcUserDetailsManager.userExists("user22")){
                jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder().encode("1234")).roles("USER").build());
            }
        };
    }
@Bean
    CommandLineRunner commandLineRunner(AccountService accountService){
        return args -> {
           /* accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","1234","user1@gmail.com", "1234");
            accountService.addNewUser("user2","1234","user2@gmail.com", "1234");
            accountService.addNewUser("admin","1234","admin@gmail.com", "1234");
            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");*/


        };
    }

}
