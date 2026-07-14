package com.Backend.Spring.Service;

import com.Backend.Spring.DTO.Request.User.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    public String verify(UserLoginRequest userLoginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUsername(),
                        userLoginRequest.getPassword()
                ));

        if(authentication.isAuthenticated())
            return "succes";

        return "failure";
    }



}
