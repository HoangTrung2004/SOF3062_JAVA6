package web.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service("auth")
public class AuthService {
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUsername (){
        return getAuthentication().getName();
    }

    public List<String> getRoles(){
        return this.getAuthentication().getAuthorities().stream()
                .map(au -> au.getAuthority().substring(5))
                .toList();

    }

    public Boolean isAuthenticated(){
        String username = getUsername();
        return username != null && !username.equals("Người ẩn danh");
    }


    public Boolean hasAnyRole(String... rolesToCheck){
        var grantedRoles =this.getRoles();
        return Stream.of(rolesToCheck)
                .anyMatch(role -> grantedRoles.contains(role));
    }

}
