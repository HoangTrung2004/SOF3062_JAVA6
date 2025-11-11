package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.entity.User;

@Service
public class DaoUserDetailsService implements UserDetailsService {

    @Autowired
    UserDAO dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = dao.findById(username).get();
        String pass = user.getPassword();
        String[] roles = user.getUserRoles().stream().map(u -> u
                .getRole().getId().substring(5)).toArray(String[]::new);
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(pass)
                .roles(roles)
                .build();
    }







}
