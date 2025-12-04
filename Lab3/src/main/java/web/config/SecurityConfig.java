package web.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pe) {
        var password = pe.encode("123");
        var user = User.withUsername("user").password(password).roles("USER").build();
        var admin = User.withUsername("admin").password(password).roles("USER", "ADMIN").build();
        var both = User.withUsername("both").password(password).roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin, both);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/error.html").permitAll()
//                .requestMatchers("/poly/url1").authenticated()
//                .requestMatchers("/poly/url2").hasRole("USER")
//                .requestMatchers("/poly/url3").hasRole("ADMIN")
//                .requestMatchers("/poly/url4").hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .permitAll());
        http.formLogin(login -> login
                .loginPage("/login/form")
                .loginProcessingUrl("/login/check").permitAll());

        // Login Google
        http.oauth2Login(oauth -> oauth
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
                    String userEmail = user.getEmail();
                    String role = "USER";

                    var newUser = User.withUsername(userEmail).password("{noop}").roles(role).build();
                    Authentication newauth = new UsernamePasswordAuthenticationToken(newUser, null, newUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newauth);
                    // dùng để chuyển tiếp trang URL
                    HttpSession session = request.getSession();
                    String attr = "SPRING_SECURITY_SAVED_REQUEST";
                    DefaultSavedRequest req = (DefaultSavedRequest) session.getAttribute(attr);
                    String redirectUrl = (req == null) ? "/" : req.getRedirectUrl();
                    response.sendRedirect(redirectUrl);
                }));

        http.logout(logout -> logout
                .logoutSuccessUrl("/"));

        http.exceptionHandling(config -> config
                .accessDeniedPage("/error"));
        http.rememberMe(rm -> rm
                .tokenValiditySeconds(2 * 24 * 60 * 60)
                .rememberMeCookieName("remember-me"));

        return http.build();
    }
}
