package com.Backend.Spring.Controller;


import com.Backend.Spring.DTO.Request.User.UserLoginRequest;
import com.Backend.Spring.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/Login")
    public String login(@RequestBody UserLoginRequest userLoginRequest){

        return authenticationService.verify(userLoginRequest);
    }



}
