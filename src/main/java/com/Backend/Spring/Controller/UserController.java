package com.Backend.Spring.Controller;

import com.Backend.Spring.DTO.Response.UResponse;
import com.Backend.Spring.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/ActiveUsers")
    public ResponseEntity<List<UResponse>> getAllActiveUsers(){

        List<UResponse> data =  userService.getAllActiveUsers();

        if(data.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);

    }

}
