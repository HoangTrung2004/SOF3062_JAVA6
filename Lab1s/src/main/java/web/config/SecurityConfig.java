package web.config;

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


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pe){
        String password = pe.encode("123");
        var user1 = User.withUsername("admin@Gmail.com").password(password).roles("ADMIN").build();
        UserDetails user2 = User.withUsername("user@gmail.com").password(password).roles("USER").build();
        var user3 = User.withUsername("both@gmail.com").password(password).roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user1,user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors ->cors.disable());

        http.authorizeHttpRequests(config ->config
                .requestMatchers("/poly/**").authenticated()
                .anyRequest().permitAll());

        http.formLogin(config ->config
                .loginPage("/login/form")
                .loginProcessingUrl("/login/check")
                .defaultSuccessUrl("/login/success")
                .failureUrl("/login/failure")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll());

        http.rememberMe( config ->config
                .tokenValiditySeconds(3*24*60*60)
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me"));

        http.logout(logout ->logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/form")
                .permitAll());

        return http.build();

    }
}
