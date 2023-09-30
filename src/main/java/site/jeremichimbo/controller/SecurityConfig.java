package site.jeremichimbo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico", "/static/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corConfig = new CorsConfiguration();
        corConfig.setAllowedOrigins(Arrays.asList("*"));
        corConfig.setAllowedMethods(Arrays.asList("*"));
        corConfig.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corConfig);
        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf().disable()
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/heartbeat", "/", "index.html", "/book/*").permitAll()
                        .requestMatchers("/bookshelf", "/bookshelf/*", "/booklist", "/booklist/*", "/account", "/account/*")
                        .authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout().logoutSuccessUrl("/").permitAll();
        return http.build();
    }
}
