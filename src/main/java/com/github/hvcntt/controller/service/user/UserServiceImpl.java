package com.github.hvcntt.controller.service.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduychuong on 8/8/2016.
 */
@Service(value = "userDetailsService")
public class UserServiceImpl implements UserDetailsService {

    public UserServiceImpl() {


    }

    private void initUsers() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userAdmin = new User("hvcntt","12345",grantedAuthorities );
        this.users.add(userAdmin);
    }

    private List<UserDetails> users = new ArrayList<UserDetails>();

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        initUsers();
        for (UserDetails userDetails : users) {
            if(userDetails.getUsername().equals(s)) {
                return userDetails;
            }
        }
       throw new UsernameNotFoundException(s);
    }
}
