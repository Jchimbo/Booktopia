package oswego.webservices.Homework7.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeHttpRequests((auth) ->auth
                        .requestMatchers("/booklist/*", "/account/*").authenticated()
                        .requestMatchers("/book/*", "/index.html").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }





}
