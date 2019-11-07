package com.example.bootdemo.mapper;

import com.example.bootdemo.vo.AuthInfo;
import com.example.bootdemo.vo.CustomUserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper {

    CustomUserDetails getUserById(String username);

    Boolean selectExistsByUserId(AuthInfo info);

    int insertAuthInfo(AuthInfo info);

}
