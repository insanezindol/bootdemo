package com.example.bootdemo.service;

import com.example.bootdemo.mapper.AuthMapper;
import com.example.bootdemo.vo.AuthInfo;
import com.example.bootdemo.vo.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements UserDetailsService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails user = authMapper.getUserById(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public Boolean selectExistsByUserId(AuthInfo info) {
        return authMapper.selectExistsByUserId(info);
    }

    public int insertAuthInfo(AuthInfo info) {
        return authMapper.insertAuthInfo(info);
    }

}