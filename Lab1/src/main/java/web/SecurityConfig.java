package web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Queue;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pe){
        String pw = pe.encode("123");
        var user1 = User.withUsername("user@gmail.com").password(pw).authorities("ROLE_USER").build();
        var user2 = User.withUsername("admin@gmail.com").password(pw).roles("ADMIN").build();  //   khai báo var SAU đấu bằng kiểu dữ liệu gì là chính nó
        UserDetails user3 = User.withUsername("both@gmail.com").password(pw).roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user1,user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .cors(cors ->cors.disable());

        //      Bài 1
        // http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        //      Bài 2
        http.authorizeHttpRequests(auth ->auth
                .requestMatchers("/poly/**").authenticated()
                .anyRequest().permitAll());

        http.formLogin(form -> form
                .loginPage("/login/form")
                .loginProcessingUrl("/login/check")
                .defaultSuccessUrl("/login/success")
                .failureUrl("/login/failure")
                .permitAll());

        http.rememberMe(rm -> rm.tokenValiditySeconds(2 * 24 * 60 * 60));

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/exit")
                .permitAll());
        return http.build();

    }
}
