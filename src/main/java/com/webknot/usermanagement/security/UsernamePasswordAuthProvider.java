package com.webknot.usermanagement.security;

import com.webknot.usermanagement.model.User;
import com.webknot.usermanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        System.out.println("email: " + email);
        System.out.println("pwd: " + pwd);

        User user = userRepository.findByEmail(email);

        if(user != null && user.getId() > 0 && passwordEncoder.matches(pwd, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    user.getEmail(), user.getPassword(), getGrantedAuthorities(user.getRole())
            );
        }



        throw new BadCredentialsException("Invaid Credentials");
    }


    private List<GrantedAuthority> getGrantedAuthorities(User.Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
