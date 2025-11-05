package web.config;

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
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pe){
        String pass = pe.encode("123");
        var user1 = User.withUsername("user").password(pass).roles("USER").build();
        UserDetails user2 = User.withUsername("admin").password(pass).authorities("ROLE_ADMIN").build();
        var user3 = User.withUsername("both").password(pass).roles("USER, ADMIN").build();
        return new InMemoryUserDetailsManager(user1,user2,user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors ->cors.disable());

        http.authorizeHttpRequests(auth ->auth
                .requestMatchers("/poly/**").authenticated()
        .anyRequest().permitAll());

        http.formLogin(form ->form
                .loginPage("/login/form")
                .loginProcessingUrl("/login/check")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login/failure")
                .permitAll());
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/exit")
                .permitAll());
        http.rememberMe(rm -> rm.tokenValiditySeconds(2*24*60*60));

        return http.build();
    }
}
