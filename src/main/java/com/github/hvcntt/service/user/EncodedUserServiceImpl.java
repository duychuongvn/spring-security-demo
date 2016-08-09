package com.github.hvcntt.service.user;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynhduychuong on 8/9/2016.
 */
public class EncodedUserServiceImpl implements UserDetailsService, InitializingBean {

    @Autowired
    private StandardPasswordEncoder standardPasswordEncoder;
    private List<UserDto> userDtos = new ArrayList<UserDto>();

    private void initUsers() {
        UserDto userenabled = new UserDto("userenabled", standardPasswordEncoder.encode("12345"), true, true, true, true);
        userenabled.addRole("ROLE_ADMIN");
        UserDto userdisabled = new UserDto("userdisabled", standardPasswordEncoder.encode("12345"), false, true, true, true);
        userdisabled.addRole("ROLE_ADMIN");
        UserDto userlocked = new UserDto("userlocked", standardPasswordEncoder.encode("12345"), true, false, true, true);
        userlocked.addRole("ROLE_ADMIN");
        this.userDtos.add(userenabled);
        this.userDtos.add(userdisabled);
        this.userDtos.add(userlocked);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (UserDto userDto : userDtos) {
            if (userDto.getUsername().equalsIgnoreCase(username)) {
                List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(userDto);
                return new User(userDto.getUsername(), userDto.getPassword(), userDto.isEnabled(), userDto.isAccountNonExpired(), userDto.isCredentialsNonExpired(),
                        userDto.isAccountNonLocked(), grantedAuthorities);
            }
        }
        throw new UsernameNotFoundException(username);
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDto userDto) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (String role : userDto.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return grantedAuthorities;
    }

    public void afterPropertiesSet() throws Exception {
        initUsers();
    }
}
