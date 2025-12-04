package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "poly/url0").permitAll()
                        .requestMatchers("/poly/url1").authenticated()
                        .requestMatchers("/poly/url2").hasRole("USER")
                        .requestMatchers("/poly/url3").hasRole("ADMIN")
                        .requestMatchers("/poly/url4").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        );
        return http.build();
    }
}
