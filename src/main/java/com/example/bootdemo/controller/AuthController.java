package com.example.bootdemo.controller;


import com.example.bootdemo.service.AuthService;
import com.example.bootdemo.util.AuthUtil;
import com.example.bootdemo.vo.AuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private AuthService authService;

    @GetMapping(value = "/join")
    public JSONObject join(@RequestBody AuthInfo info) {
        JSONObject obj = new JSONObject();

        if (authService.selectExistsByUserId(info)) {
            obj.put("code", "100400");
            obj.put("msg", "failure");
            obj.put("data", "already exist by id");
        } else {
            info.setPassword(AuthUtil.encrypt(info.getPassword()));
            int resultCnt = authService.insertAuthInfo(info);
            obj.put("code", "100200");
            obj.put("msg", "success");
            obj.put("data", resultCnt);
        }

        return obj;
    }

    @GetMapping(value = "/authenticate")
    public JSONObject createAuthenticationToken(@RequestBody AuthInfo info) throws Exception {
        JSONObject obj = new JSONObject();

        authenticate(info.getUsername(), info.getPassword());
        UserDetails userDetails = authService.loadUserByUsername(info.getUsername());
        String token = authUtil.generateToken(userDetails);

        obj.put("code", "100200");
        obj.put("msg", "success");
        obj.put("data", token);

        return obj;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}