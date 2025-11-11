package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import web.service.DaoUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    //      BÀI 1
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder pe) {
//        String pass = pe.encode("123");
//        var user  = User.withUsername("user@gmail.com").password(pass).roles("USER").build();
//        var admin = User.withUsername("admin@gmail.com").password(pass).roles("ADMIN").build();
//        var both  = User.withUsername("both@gmail.com").password(pass).roles("USER","ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin, both);
//    }

    //      BÀI 2
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//
//    }

    //      BÀI 3

    public UserDetailsService userDetailsService() {
        return new DaoUserDetailsService();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/error", "/error.html").permitAll()
                .requestMatchers("/poly/url1").authenticated()
                .requestMatchers("/poly/url2").hasRole("USER")
                .requestMatchers("/poly/url3").hasRole("ADMIN")
                .requestMatchers("/poly/url4").hasAnyRole("USER","ADMIN")
                .anyRequest().permitAll()
        );
        http.exceptionHandling(config->config.accessDeniedPage("/error.html"));

        http.formLogin(form -> form.permitAll());
        http.logout(logout -> logout
                .logoutSuccessUrl("/"));
        return http.build();
    }
}
