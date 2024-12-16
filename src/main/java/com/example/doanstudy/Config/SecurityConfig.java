package com.example.doanstudy.Config;

import com.example.doanstudy.PassWordEncoder.BCryptPasswordEncoder2;
import com.example.doanstudy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AdminService adminService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder2 ec = new BCryptPasswordEncoder2();
        ec.setUserId(adminService);
        return ec;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/style.css", "/img/**", "/static/**", "/fonts/**", "/lib/**","/fonts/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider())  // Thêm dòng này
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Home/**").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/style.css", "/img/**", "/static/**", "/fonts/**", "/lib/**","/fonts/**").permitAll()
                        .requestMatchers("/Home/login").permitAll()  // Thêm dòng này
                        .requestMatchers("/admin/lession/**","/admin/categories/**","/admin/course/**","/admin/coursepart/**").authenticated()
                        .anyRequest().authenticated()  // Thêm dòng này
                )
                .formLogin(form -> form
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin/dashboard", true)
                        .failureUrl("/admin/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")  // Sửa lại URL logout
                        .logoutSuccessUrl("/admin/login")  // Sửa lại URL after logout
                        .permitAll()
                );


        return http.build();
    }
}
