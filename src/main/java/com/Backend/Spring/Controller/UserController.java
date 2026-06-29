package com.Backend.Spring.Controller;

import com.Backend.Spring.DTO.Request.User.UCreateRequest;
import com.Backend.Spring.DTO.Response.UResponse;
import com.Backend.Spring.Model.Entity.UserModel;
import com.Backend.Spring.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/CreateUser")
    public ResponseEntity<UResponse> createUser(@RequestBody UCreateRequest request){

        UResponse resp = userService.createUser(request);

        return ResponseEntity.ok(resp);

    }

    @PostMapping("/Register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UCreateRequest request){
        UserModel user = userService.register(request);

        return ResponseEntity.ok(user);
    }

}
