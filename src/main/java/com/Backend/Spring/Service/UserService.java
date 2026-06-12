package com.Backend.Spring.Service;

import com.Backend.Spring.DTO.Request.User.UCreateRequest;
import com.Backend.Spring.DTO.Response.UResponse;
import com.Backend.Spring.Mapper.UserMapper;
import com.Backend.Spring.Model.Entity.UserModel;
import com.Backend.Spring.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UResponse> getAllActiveUsers(){

        return userRepository.findByStatus("ACTIVE").stream()
                .map(userMapper :: toDTO)
                .collect(Collectors.toList());

    }

    @Transactional
    public UResponse createUser(UCreateRequest request){

        UserModel savedUser =  userMapper.toModel(request);

        savedUser.setStatus("ACTIVE");

        UserModel newUser = userRepository.save(savedUser);

        return userMapper.toDTO(newUser);
    }
}
