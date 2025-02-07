package com.example.patientspringmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    // Configuration des utilisateurs en mémoire
   /* @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();
        // Encodage du mot de passe pour admin
        String encodedPassword = encoder.encode("1234");
        System.out.println("Encoded password for '1234': " + encodedPassword); // Affichage du mot de passe encodé
        // Création des utilisateurs avec leurs rôles

        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("1234")) // Encodage du mot de passe
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("USER", "ADMIN")
                .build();


        // Retourne un gestionnaire d'utilisateurs en mémoire
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
*/
   // @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER", "ADMIN").build()
        );
    }
    // Configuration des règles de sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll().defaultSuccessUrl("/")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                        .logoutSuccessUrl("/"))
                .rememberMe(rememberMe -> rememberMe
                        .rememberMeParameter("remember-me")
                )
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers("/admin/**").hasRole("ADMIN")
                        //.requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403")
                );

        return httpSecurity.build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // Configuration de l'encodeur de mot de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
}