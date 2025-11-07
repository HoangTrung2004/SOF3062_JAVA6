package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import web.service.DaoUserDetailsService;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
@Service
public class SecurityConfig {

    //  BÀI 1
    /*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService(PasswordEncoder pe){
        String pass = pe.encode("123");
        var user = User.withUsername("user").password(pass).roles("USER").build();
        var admin  = User.withUsername("admin").password(pass).roles("ADMIN").build();
        UserDetails both = User.withUsername("both").password(pass).roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin, both);
    }      */


//    //  BÀI 2
//    @Bean
//    public UserDetailsService userDetailService (DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    // BÀI 3
    @Autowired
    DaoUserDetailsService daoUserDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        http.userDetailsService(daoUserDetailsService); //BÀI 3

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/error","/login").permitAll()
                .requestMatchers("/poly/url1").authenticated()
                .requestMatchers("/poly/url2").hasRole("USER")
                .requestMatchers("/poly/url3").hasRole("ADMIN")
                .requestMatchers("/poly/url4").hasAnyRole("USER","ADMIN")
                .anyRequest().permitAll());
        http.exceptionHandling(e -> e
                // Đây là chỗ bạn định nghĩa trang sẽ hiển thị khi Access Denied (Lỗi 403)
                .accessDeniedPage("/error")
        );
        http.formLogin(form ->form.permitAll());

        http.logout(logout ->logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/"));
        return http.build();


    }
}
